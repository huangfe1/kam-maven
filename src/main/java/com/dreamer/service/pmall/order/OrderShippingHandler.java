package com.dreamer.service.pmall.order;

import com.dreamer.domain.mall.goods.MallGoods;
import com.dreamer.domain.pmall.order.Order;
import com.dreamer.repository.mall.goods.MallGoodsDAO;
import com.dreamer.repository.pmall.order.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderShippingHandler {

	@Transactional
	public void shipping(Order order,Double actual_logisticsFee){
//		deductGoodsStock(order);//发货不减少库存
		if(actual_logisticsFee>order.getLogisticsFee()){
            BigDecimal b1 = new BigDecimal(order.getLogisticsFee());
            BigDecimal b2 = new BigDecimal(actual_logisticsFee);
            Double temp=b2.subtract(b1).doubleValue();
			order.getUser().getAccounts().deductLogistFee(temp,"扣减物流费,订单"+order.getOrderNo());
			order.setLogisticsFee(actual_logisticsFee);//设置实际物流费
		}
		order.shipping();
		orderDAO.merge(order);
	}

	@Transactional
	public void shipping(Order order){
//		deductGoodsStock(order);//发货不减少库存
		order.shipping();
		orderDAO.merge(order);
	}
	
	@Transactional
	public void deductGoodsStock(Order order){
		order.getItems().entrySet().stream().forEach(entry->{
			MallGoods goods=mallGoodsDAO.findById(entry.getKey());
			goods.deductCurrentStock(entry.getValue().getQuantity());
		});
	}
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private MallGoodsDAO mallGoodsDAO;
}
