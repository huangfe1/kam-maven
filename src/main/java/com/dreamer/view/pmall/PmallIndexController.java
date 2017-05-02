package com.dreamer.view.pmall;

import com.dreamer.domain.mall.goods.MallGoods;
import com.dreamer.domain.mall.goods.MallGoodsType;
import com.dreamer.domain.user.Agent;
import com.dreamer.domain.user.User;
import com.dreamer.repository.mall.goods.MallGoodsDAO;
import com.dreamer.repository.mall.goods.MallGoodsTypeDAO;
import com.dreamer.repository.user.AgentDAO;
import com.dreamer.service.pay.GetOpenIdHandler;
import com.dreamer.service.pay.JsApiParameterFactory;
import com.dreamer.service.pay.PayConfig;
import com.dreamer.util.TokenInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ps.mx.otter.utils.SearchParameter;
import ps.mx.otter.utils.WebUtil;
import ps.mx.otter.utils.json.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = {"/pmall", "/dmz/pmall"})
public class PmallIndexController {

    @RequestMapping(value = {"/index.html", "/", "/index"}, method = RequestMethod.GET)
    public String index(
            @ModelAttribute("parameter") SearchParameter<MallGoods> param,
            @RequestParam(value = "ref", required = false) String ref,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        try {
            if (WebUtil.isLogin(request)) {
                User user = (User) WebUtil.getCurrentUser(request);
                Agent agent = agentDAO.findById(user.getId());
                model.addAttribute("account_points", agent.getAccounts().getPointsBalance());
                model.addAttribute("myCode", agent.getAgentCode());//加入自己的编号
            }
//			if(Objects.nonNull(ref)){
//				model.addAttribute("agentCode", ref);
//			}
        } catch (Exception exp) {
            LOG.error("进入积分商城首页异常", exp);
            exp.printStackTrace();
        }
        return "pmall/pmall_index";
    }

    @RequestMapping(value = "/tc/index.html", method = RequestMethod.GET)
    public String tc_index(@RequestParam(required = false) Integer pType,@RequestParam(required = false) Integer mallType, Model model) {
        //首先找出所有分类
        List<MallGoodsType> list = mallGoodsTypeDAO.findAll(1);
        if (list != null && !list.isEmpty()) {//找出今日主推
            List<MallGoods> ms = mallGoodsDAO.findByType(list.get(0).getId());
            List<PointsGoodsDTO> dtos = new ArrayList<PointsGoodsDTO>();
            ms.forEach(g -> {
                PointsGoodsDTO dto = new PointsGoodsDTO();
                dto.setId(g.getId());
                dto.setBenefitPoints(g.getBenefitPoints());
                dto.setMoneyPrice(g.getMoneyPrice());
                dto.setName(g.getName());
                dto.setShareName(g.getShareName());
                dto.setShareDetail(g.getShareDetail());
                dto.setPointPrice(g.getPointPrice());
                dto.setPrice(g.getPrice());
                dto.setSpec(g.getSpec());
                dto.setStockQuantity(g.getStockQuantity());
                dto.setVoucher(g.getVoucher());
                dto.setVouchers(g.getVouchers());
                String imgUrl = TokenInfo.IMG_HEAD_PATH + "/dmz/img/pmall/"
                        + g.getImgFile();
                dto.setImgUrl(imgUrl);
                if(mallType!=null&&mallType==0){//优惠商城
                    if(dto.getMoneyPrice()!=0){
                        dtos.add(dto);
                    }
                }else {//置换系统
                    if(dto.getMoneyPrice()==0){
                        dtos.add(dto);
                    }
                }

            });
            model.addAttribute("ztGoods", dtos);
            list.remove(0);
            if (pType == null) {
                pType = list.get(0).getId();
            }
            //返回当前查找的类型名字
            final Integer pid = pType;
            list.forEach(l->{
                if(l.getId().equals(pid)){
                    model.addAttribute("pNames",l.getName());
                }
            });

            model.addAttribute("pType", pType);//首次只展示这种分类
            model.addAttribute("pTypes", list);//所有的types
        }
//
//			List<MallGoods> list = mallGoodsDAO.findAll(0,4);
//			List<PointsGoodsDTO> dtos = new ArrayList<>();
//		list.forEach(g->{
//			PointsGoodsDTO dto=new PointsGoodsDTO();
//			dto.setId(g.getId());
//			dto.setName(g.getName());
//			dto.setPointPrice(g.getPointPrice());
//			dto.setPrice(g.getPrice());
//			dto.setSpec(g.getSpec());
//			dto.setStockQuantity(g.getStockQuantity());
//			dto.setVoucher(g.getVoucher());
//			dto.setVouchers(g.getVouchers());
////            String imgUrl = WebUtil.getContextPath(request) + "/dmz/img/pmall/"
////					+ g.getImgFile();
//			String imgUrl = TokenInfo.IMG_HEAD_PATH + "/dmz/img/pmall/"
//					+ g.getImgFile();
//			dto.setImgUrl(imgUrl);
//			dtos.add(dto);
//		});
//			model.addAttribute("list",dtos);
        return "pmall/tc_index";
    }


    @RequestMapping(value = "/tc/type.html", method = RequestMethod.GET)
    public String tc_type(Integer parentType, Model model) {
        List<MallGoodsType> list = mallGoodsTypeDAO.findAllByParent(parentType);
        List<PointsGoodsDTO> dtos = new ArrayList<>();
        list.forEach(g -> {
            PointsGoodsDTO dto = new PointsGoodsDTO();
            dto.setId(g.getId());
            dto.setName(g.getName());
            dto.setType(g.getType());
//            String imgUrl = WebUtil.getContextPath(request) + "/dmz/img/pmall/"
//					+ g.getImgFile();
            String imgUrl = TokenInfo.IMG_HEAD_PATH + "/dmz/img/pmall/"
                    + g.getImg();
            dto.setImgUrl(imgUrl);
            dtos.add(dto);
        });
        model.addAttribute("list", dtos);
        return "pmall/tc_type";
    }

    @RequestMapping(value = "/detail.html", method = RequestMethod.GET)
    public String detail(@RequestParam("id") Integer id, HttpServletRequest request, String myCode, Model model) {
        try {
            MallGoods g = mallGoodsDAO.findById(id);
            PointsGoodsDTO dto = new PointsGoodsDTO();
            dto.setId(g.getId());
            dto.setBenefitPoints(g.getBenefitPoints());
            dto.setMoneyPrice(g.getMoneyPrice());
            dto.setName(g.getName());
            dto.setPointPrice(g.getPointPrice());
            dto.setPrice(g.getPrice());
            dto.setSpec(g.getSpec());
            dto.setStockQuantity(g.getStockQuantity());
            dto.setShareDetail(g.getShareDetail());
            dto.setShareName(g.getShareName());
            dto.setVoucher(g.getVoucher());
//			String imgUrl = WebUtil.getContextPath(request) + "/dmz/img/pmall/"
//					+ g.getImgFile();
            String imgUrl = TokenInfo.IMG_HEAD_PATH + "/dmz/img/pmall/"
                    + g.getImgFile();
            dto.setImgUrl(imgUrl);
            List<String> list = new ArrayList<>();
            if (g.getDetailImg() != null) {
                for (String str : g.getDetailImg().split("\\+")) {
//					list.add(WebUtil.getContextPath(request) + "/dmz/img/goods/"+str);
                    list.add(TokenInfo.IMG_HEAD_PATH + "/dmz/img/goods/" + str);
                }
            }
            dto.setDetailImgUrls(list);//添加详情页
            String url = ServletUriComponentsBuilder.fromRequest(request).toUriString();
            HashMap jspmap = jsApiParameterFactory.buildShare(payConfig, url, TokenInfo.getJsapiTicket());
            String jsonParam = JsonUtil.mapToJsonStr(jspmap);
//            LOG.debug("JSAPI Edit address Param：{}",jsonParam);
            if (WebUtil.isLogin(request)) {
                User user = (User) WebUtil.getCurrentUser(request);
                Agent agent = agentDAO.findById(user.getId());
                myCode = agent.getAgentCode();
            }
            model.addAttribute("myCode", myCode);//传获取parent的
//			System.out.println("--------hfCode"+myCode);
            model.addAttribute("jsapiParamJson", jsonParam);
            model.addAttribute("goods", dto);
        } catch (Exception exp) {
            LOG.error("进入积分商城商品详情页异常", exp);
            exp.printStackTrace();
        }
        return "pmall/goods_detail";
    }


    @RequestMapping(value = "/goods/query.json", method = RequestMethod.GET)
    @ResponseBody
    public List<PointsGoodsDTO> queryGoods(
            @ModelAttribute("parameter") SearchParameter<MallGoods> param,
            @RequestParam(required = false) Integer mallType) {
        param.getEntity().setShelf(true);
        List<MallGoods> goods = mallGoodsDAO.searchEntityByPage(param, null,
                (t) -> true,mallType);
        List<PointsGoodsDTO> dtos = new ArrayList<PointsGoodsDTO>();
        goods.forEach(g -> {
            PointsGoodsDTO dto = new PointsGoodsDTO();
            dto.setId(g.getId());
            dto.setBenefitPoints(g.getBenefitPoints());
            dto.setMoneyPrice(g.getMoneyPrice());
            dto.setName(g.getName());
            dto.setPointPrice(g.getPointPrice());
            dto.setPrice(g.getPrice());
            dto.setSpec(g.getSpec());
            dto.setStockQuantity(g.getStockQuantity());
            dto.setVoucher(g.getVoucher());
            dto.setVouchers(g.getVouchers());
//            String imgUrl = WebUtil.getContextPath(request) + "/dmz/img/pmall/"
//					+ g.getImgFile();
            String imgUrl = TokenInfo.IMG_HEAD_PATH + "/dmz/img/pmall/"
                    + g.getImgFile();
            dto.setImgUrl(imgUrl);
            dtos.add(dto);
        });
        return dtos;
    }

    @ModelAttribute("parameter")
    public SearchParameter<MallGoods> preprocessing() {
        SearchParameter<MallGoods> param = new SearchParameter<MallGoods>();
        MallGoods goods = new MallGoods();
        param.setEntity(goods);
        return param;
    }

    @Autowired
    private JsApiParameterFactory jsApiParameterFactory;

    @Autowired
    private MallGoodsDAO mallGoodsDAO;


    @Autowired
    private MallGoodsTypeDAO mallGoodsTypeDAO;

    @Autowired
    private PayConfig payConfig;

    @Autowired
    private GetOpenIdHandler getOpenIdHandler;
    @Autowired
    private AgentDAO agentDAO;

    private final Logger LOG = LoggerFactory.getLogger(getClass());
}
