package com.dreamer.repository.account;

import com.dreamer.domain.account.LogistFeeRecord;
import com.dreamer.domain.user.Agent;
import com.dreamer.domain.user.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import ps.mx.otter.utils.SearchParameter;
import ps.mx.otter.utils.dao.hibernate.HibernateBaseDAO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository("logistFeeRecordDAO")
public class LogistFeeRecordDAO extends HibernateBaseDAO<LogistFeeRecord>{
	private static final Logger log = LoggerFactory.getLogger(LogistFeeRecordDAO.class);


	private Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

//	public List<LogistFeeRecord> findRecordsForUser(User user){
//		log.debug("finding LogistFeeRecord instance by user");
//		try {
//			String queryString = "from LogistFeeRecord as model where model.user.id= ?";
//			Query queryObject = getCurrentSession().createQuery(queryString);
//			queryObject.setParameter(0, user.getId());
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find by user failed", re);
//			throw re;
//		}
//	}

	/**
	 * 获取没有通知的代金券记录
	 * @return
     */
	public List<LogistFeeRecord> getNeedNoticeRecord(){
		String hql="from LogistFeeRecord where hasNoticed = 0";
		Query query =getCurrentSession().createQuery(hql);
		return query.list();
	}


	/**
	 * 代理查询
	 * @param p
	 * @param parent
     * @return
     */
	public List<LogistFeeRecord> searchEntityByPage(SearchParameter<LogistFeeRecord> p,
												  User parent) {
		// TODO Auto-generated method stub
		return super.searchEntityByPage(
				p,
				(t) -> {
					Example example = Example.create(t.getEntity()).enableLike(
							MatchMode.START);
					Criteria criteria =getCurrentSession()
							.createCriteria(LogistFeeRecord.class);
                    criteria.add(example);
                    criteria.addOrder(Order.desc("updateTime"));//根据时间排序
                    criteria.addOrder(Order.desc("fee_now"));//根据金钱排序 钱越来越多
                    if(Objects.nonNull(parent)){//上级不为空
//                        t.getEntity().setAgent((Agent) parent);
                        criteria.createCriteria("agent").add(Restrictions.eq("id",parent.getId()));
                    }
//                    if(t.getEntity().getAgent()!=null&&!t.getEntity().getAgent().getRealName().equals("")){
//                        criteria.createCriteria("agent").add(Example.create(t.getEntity().getAgent()));
//                    }
					//criteria.addOrder(Order.desc("vr.id"));//倒序
					return criteria;
				}, null);
	}

	/**
	 * 管理员
	 * @param p
     * @return
     */
	public List<LogistFeeRecord> searchEntityByPage(SearchParameter<LogistFeeRecord> p) {
		// TODO Auto-generated method stub
		return super.searchEntityByPage(
				p,
				(t) -> {
					Example example = Example.create(t.getEntity()).enableLike(
							MatchMode.ANYWHERE);
					Agent agent=t.getEntity().getAgent();
					Criteria criteria = getCurrentSession()
							.createCriteria(LogistFeeRecord.class);
					criteria.add(example);
					criteria.addOrder(Order.desc("updateTime"));//时间排序
					criteria.addOrder(Order.asc("type"));//积分量排序
					criteria.addOrder(Order.asc("fee"));//积分量排序
					if(t.getEntity().getAgent()!=null&&!t.getEntity().getAgent().getRealName().equals("")){
                        criteria.createCriteria("agent").add(Restrictions.eq("realName",t.getEntity().getAgent().getRealName()));
					}
					return criteria;
				}, null);
	}


	public void save(LogistFeeRecord transientInstance) {
		log.debug("saving GoodsAccount instance");
		try {
			transientInstance.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}




//	public List<GoodsAccount> findByExample(LogistFeeRecord instance) {
//		log.debug("finding GoodsAccount instance by example");
//		try {
//			List results = getCurrentSession()
//					.createCriteria("com.dreamer.domain.account.LogistFeeRecord")
//					.add(Example.create(instance)).list();
//			log.debug("find by example successful, result size: "
//					+ results.size());
//			return results;
//		} catch (RuntimeException re) {
//			log.error("find by example failed", re);
//			throw re;
//		}
//	}




	public LogistFeeRecord merge(LogistFeeRecord detachedInstance) {
		log.debug("merging LogistFeeRecord instance");
		try {
			detachedInstance.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			LogistFeeRecord result = (LogistFeeRecord) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	public static LogistFeeRecordDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LogistFeeRecordDAO) ctx.getBean("LogistFeeRecordDAO");
	}
}