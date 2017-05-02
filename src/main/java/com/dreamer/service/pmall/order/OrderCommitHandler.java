package com.dreamer.service.pmall.order;

import com.dreamer.domain.mall.goods.MallGoods;
import com.dreamer.domain.pmall.order.Order;
import com.dreamer.domain.pmall.order.OrderItem;
import com.dreamer.domain.user.Agent;
import com.dreamer.repository.mall.goods.MallGoodsDAO;
import com.dreamer.repository.pmall.order.OrderDAO;
import com.dreamer.repository.user.AgentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ps.mx.otter.exception.ApplicationException;

import java.util.List;

@Service
public class OrderCommitHandler {

	@Transactional(propagation = Propagation.REQUIRED)
	public Order commitOrder(Order order,Agent agent,List<OrderItem> items){
		LOG.debug("进入积分商城订单提交");

		if(agent.getAccounts().getLogisticsFeeBalance()<8){
			throw new ApplicationException("运费不足,请充值至少8元");
		}

		try{
			items.stream().forEach(item->{
                //判断库存是否充足
                MallGoods mg =mallGoodsDAO.findById(item.getGoodsId());//找出当前货物
                if(mg.getStockQuantity()<item.getQuantity()){//库存不够
                    throw new ApplicationException(mg.getName()+"库存不足");
                }
				item.setOrder(order);
				order.addItem(item);
			});
			order.commit(agent);
            agentDAO.save(agent);
			orderDAO.save(order);//TODO  这里可以省去一个
//			order.getItems().clear();//释放空间
//			order=null;
		}catch(ApplicationException aep){
			LOG.error("积分商城订单提交失败",aep);
			aep.printStackTrace();
			throw aep;
		}catch(Exception exp){
			LOG.error("积分商城订单提交异常",exp);
			exp.printStackTrace();
			throw new ApplicationException(exp);
		}
		
		return order;
	}
	
	
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private AgentDAO agentDAO;
    @Autowired
    private MallGoodsDAO mallGoodsDAO;
	
	private final Logger LOG=LoggerFactory.getLogger(getClass());
}
