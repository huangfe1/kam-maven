package com.dreamer.view.user;

import com.dreamer.domain.user.AdvanceTransfer;
import com.dreamer.domain.user.Agent;
import com.dreamer.domain.user.LogistFeeTransfer;
import com.dreamer.domain.user.User;
import com.dreamer.repository.user.AdvanceTransferDAO;
import com.dreamer.service.pay.GetOpenIdHandler;
import com.dreamer.service.pay.PayConfig;
import com.dreamer.service.user.AgentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ps.mx.otter.utils.WebUtil;
import ps.mx.otter.utils.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/logistFee/pay")
public class LogistFeePayController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
	@RequestMapping(value = "/commit.json",method = RequestMethod.POST)
	public Message commit(LogistFeeTransfer logistFeeTransfer){
        try {
            User user=(User)WebUtil.getCurrentUser(request);
            agentHandler.addLogistFee(user,logistFeeTransfer);//提交充值置换券定订单
            WebUtil.addSessionAttribute(request,"currentOrderId",logistFeeTransfer.getId());//orderId  获取code后  统一下单需要
            String backUrl= ServletUriComponentsBuilder.fromContextPath(request).path("/logistFee/pay/pay.html").build().toUriString();
            String uri= GetOpenIdHandler.createGetBaseOpenIdCallbackUrl(payConfig, backUrl, logistFeeTransfer.getId());
            response.setHeader("Location",uri);
//            response.setHeader("backurl",backUrl);
            return Message.createSuccessMessage("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return Message.createFailedMessage(e.getMessage());
        }
	}
    @Autowired
    private AgentHandler agentHandler;
    @Autowired
    private PayConfig payConfig;
}
