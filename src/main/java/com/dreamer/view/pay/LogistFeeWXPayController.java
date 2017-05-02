package com.dreamer.view.pay;

import com.dreamer.domain.user.*;
import com.dreamer.repository.user.AccountsDAO;
import com.dreamer.repository.user.AgentDAO;
import com.dreamer.repository.user.LogistFeeTransferDAO;
import com.dreamer.service.pay.*;
import com.dreamer.service.pay.param.PayNoticeData;
import com.dreamer.service.pay.param.UnifiedOrderResData;
import com.dreamer.service.user.AgentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ps.mx.otter.exception.ApplicationException;
import ps.mx.otter.utils.WebUtil;
import ps.mx.otter.utils.json.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/logistFee/pay")
public class LogistFeeWXPayController {
    @Autowired
    private PayConfig payConfig;
    @Autowired
    private GetOpenIdHandler getOpenIdHandler;
    @Autowired
    private LogistFeeTransferDAO logistFeeTransferDAO;
    @Autowired
    private AgentHandler agentHandler;
    @Autowired
    private UnifiedOrderHandler unifiedOrderHandler;
    @Autowired
    private JsApiParameterFactory jsApiParameterFactory;
    @Autowired
    private AgentDAO agentDAO;
    @Autowired
    private AccountsDAO accountsDAO;


    @RequestMapping("/index.html")
    public String index(HttpServletRequest request, Model model) {
        User user = (User) WebUtil.getCurrentUser(request);
        Accounts acs = accountsDAO.findByAgentId(user.getId());
        model.addAttribute("accounts", acs);
        return "/user/logist_fee_pay";
    }

    @RequestMapping("/pay.html")
    public String pay(@RequestParam(required = false) String code, Model model, HttpServletRequest request) {
        try {
//            //先获取一遍openID
//            if(Objects.isNull(code)){
//                String backUrl= ServletUriComponentsBuilder.fromContextPath(request).path("/logistFee/pay/pay.html").queryParam("orderId",orderId).build().toUriString();
//                String uri= GetOpenIdHandler.createGetBaseOpenIdCallbackUrl(payConfig, backUrl, orderId);
//                return "redirect:"+uri;
//            }
            Integer orderId = (Integer) WebUtil.getSessionAttribute(request,"currentOrderId");
            LogistFeeTransfer logistFeeTransfer = logistFeeTransferDAO.findById(orderId);
            if (logistFeeTransfer.getType() == LogistFeeTransferType.PAY) {//已经支付
                model.addAttribute("errorMsg", "已经支付的订单");
                return "pay/advance_pay_error";
            }
            Agent agent = (Agent) WebUtil.getCurrentUser(request);
            agent = agentDAO.findById(agent.getId());//session中的已经过时
            HashMap<String, String> map = getOpenIdHandler.getOpenId(payConfig, code);
            String openId = map.get("openid");
            agent.setWxOpenid(openId);//设置微信ID
            agentHandler.setWxOpenIdTo(agent, openId);
            //}
            String notifyUrl = ServletUriComponentsBuilder.fromContextPath(request).path("/logistFee/pay/dmz/notify.html").build().toUriString();
//            String notifyUrl = "http://www.kam365.com/dreamer/advance/pay/dmz/notify.html";
            UnifiedOrderResData unifiedOrder = unifiedOrderHandler.unifiedOrder(notifyUrl, logistFeeTransfer.getOut_trade_no(), logistFeeTransfer.getFee(), payConfig, agent, "咖盟国际物流费充值！");

            if (unifiedOrder.getReturn_code().equals("FAIL")) {
                String errorMsg = "统一下单支付失败,请稍后重试" + unifiedOrder.getReturn_msg();
                if (openId == null || openId.equals("")) {
                    errorMsg = code + "统一下单支付失败,请稍后重试" + map.get("errmsg");
                }
                model.addAttribute("errorMsg", errorMsg);
                return "pay/advance_pay_error";
            }
            String jsonParam = JsonUtil.mapToJsonStr(jsApiParameterFactory.build(payConfig, unifiedOrder.getPrepay_id()));
            model.addAttribute("jsapiParamJson", jsonParam);
            model.addAttribute("order", logistFeeTransfer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pay/pay_logist_fee";

    }


    @RequestMapping(value = "/dmz/notify.html", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> notify(@RequestBody String body) {
        try {
            PayNoticeData resData = Util.getObjectFromXML(body, PayNoticeData.class);
            if (resData.getResult_code().toUpperCase().equals("SUCCESS")) {
                String orderNo = resData.getOut_trade_no();
                LogistFeeTransfer temp = new LogistFeeTransfer();
                temp.setOut_trade_no(orderNo);
                LogistFeeTransfer logistFeeTransfer = logistFeeTransferDAO.findByExample(temp).get(0);//获取第一个
                if (logistFeeTransfer.getType() == LogistFeeTransferType.PAY) {
                    throw new ApplicationException("订单" + orderNo + "已经支付,无需再次支付");
                }
                agentHandler.payForLogistFee(resData.getTime_end(), logistFeeTransfer);
            }
        } catch (Exception e) {
            System.out.println("订单支付失败" + e.getMessage());
            e.printStackTrace();
        }
        String ok = "<xml><return_code><![CDATA[SUCCESS]]></return_code>  <return_msg><![CDATA[OK]]></return_msg></xml>";
        return new ResponseEntity<>(ok, HttpStatus.OK);
    }


    public static void main(String[] args) {
        Double m = 0.1;
        Double y = 0.20;
        System.out.println(m.equals(y));
    }


}
