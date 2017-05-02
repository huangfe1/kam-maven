package com.dreamer.repository.user;

import com.dreamer.domain.user.LogistFeeTransfer;
import com.dreamer.domain.user.User;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
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

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Repository("logistFeeTransferDAO")
public class LogistFeeTransferDAO extends HibernateBaseDAO<LogistFeeTransfer> {
	private static final Logger log = LoggerFactory
			.getLogger(LogistFeeTransferDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REMARK = "remark";
	public static final String VOUCHER = "voucher";

	private Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}



	public List<LogistFeeTransfer> searchEntityByPage(
			SearchParameter<LogistFeeTransfer> p,
			Function<SearchParameter<LogistFeeTransfer>, ? extends Object> getSQL,
			Function<Void, Boolean> getCacheQueries,Integer agentId) {
		// TODO Auto-generated method stub
		return super.searchEntityByPage(p, (t)->{
			Example example = Example.create(t.getEntity()).enableLike(
					MatchMode.START);
			Criteria criteria =getCurrentSession()
					.createCriteria(getClazz());
			criteria.add(example);
			if (Objects.nonNull(agentId)) {
				criteria.add(Restrictions.or(Restrictions.eq("userByToAgent.id", agentId),Restrictions.eq("userByFromAgent.id", agentId)));
			} else {
				User agentTo = t.getEntity().getUserByToAgent();
				if (Objects.nonNull(agentTo)) {
					criteria.createCriteria("userByToAgent").add(
							Restrictions.idEq(agentTo.getId()));
				}
				User agentFrom = t.getEntity().getUserByFromAgent();
				if (Objects.nonNull(agentFrom)) {
					criteria.createCriteria("userByFromAgent").add(
							Restrictions.idEq(agentFrom.getId()));
				}
			}
			if(t.getEndTime()!=null || t.getStartTime()!=null){
				criteria.add(Restrictions.between("updateTime",t.getStartTimeByDate(), t.getEndTimeByDate()));
			}
			criteria.addOrder(Order.desc("id"));
			return criteria;
		}, getCacheQueries);
	}

	public void save(LogistFeeTransfer transientInstance) {
		log.debug("saving LogistFeeTransfer instance");
		try {
			transientInstance.setUpdateTime(new Date());
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LogistFeeTransfer persistentInstance) {
		log.debug("deleting LogistFeeTransfer instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LogistFeeTransfer findById(Integer id) {
		log.debug("getting LogistFeeTransfer instance with id: " + id);
		try {
			LogistFeeTransfer instance = (LogistFeeTransfer) getCurrentSession().get(
					"com.dreamer.domain.user.LogistFeeTransfer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 找出第一个订单
	 * @param id
	 * @return
     */
	public LogistFeeTransfer findByAgentId(Integer id){

		try {
			String queryString = "from LogistFeeTransfer as model where model.userByToAgent.id=? order by updateTime desc";
			Query query=getCurrentSession().createQuery(queryString);
			query.setParameter(0,id);
			query.setMaxResults(1);
			return (LogistFeeTransfer)query.uniqueResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}



	public List<LogistFeeTransfer> findByExample(LogistFeeTransfer instance) {
		log.debug("finding LogistFeeTransfer instance by example");
		try {
			List<LogistFeeTransfer> results = getCurrentSession()
					.createCriteria("com.dreamer.domain.user.LogistFeeTransfer")
					.add(Example.create(instance))
					.addOrder(Order.asc("transferTime")).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<LogistFeeTransfer> findByProperty(String propertyName, Object value) {
		log.debug("finding LogistFeeTransfer instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LogistFeeTransfer as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<LogistFeeTransfer> findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	public List<LogistFeeTransfer> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List<LogistFeeTransfer> findByPoint(Object point) {
		return findByProperty(VOUCHER, point);
	}

	public List<LogistFeeTransfer> findAll() {
		log.debug("finding all LogistFeeTransfer instances");
		try {
			String queryString = "from LogistFeeTransfer";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LogistFeeTransfer merge(LogistFeeTransfer detachedInstance) {
		log.debug("merging LogistFeeTransfer instance");
		try {
			detachedInstance.setUpdateTime(new Date());
            LogistFeeTransfer result = (LogistFeeTransfer) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LogistFeeTransfer instance) {
		log.debug("attaching dirty LogistFeeTransfer instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LogistFeeTransfer instance) {
		log.debug("attaching clean LogistFeeTransfer instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LogistFeeTransferDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LogistFeeTransferDAO) ctx.getBean("LogistFeeTransferDAO");
	}
}