package com.dreamer.repository.mall.goods;

import com.dreamer.domain.mall.goods.MallGoodsType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ps.mx.otter.utils.SearchParameter;
import ps.mx.otter.utils.dao.hibernate.HibernateBaseDAO;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Repository
public class MallGoodsTypeDAO extends HibernateBaseDAO<MallGoodsType>{
	private static final Logger log = LoggerFactory
			.getLogger(MallGoodsTypeDAO.class);

	private Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	@Override
	public List<MallGoodsType> searchEntityByPage(SearchParameter<MallGoodsType> p, Function<SearchParameter<MallGoodsType>, ?> getSQL, Function<Void, Boolean> getCacheQueries) {
		return super.searchEntityByPage(p,
				(t)->{
					Example example = Example.create(t.getEntity()).enableLike(MatchMode.ANYWHERE);
                    Criteria criteria = getCurrentSession().createCriteria(t.getEntity().getClass());
                    criteria.add(example);
                    criteria.addOrder(Order.asc("varStatus"));
                    criteria.addOrder(Order.asc("id"));
                    return criteria;
				}
				, getCacheQueries);
	}


//
//	@Override
//	public List<MallGoods> searchEntityByPage(SearchParameter<MallGoods> p,
//			Function<SearchParameter<MallGoods>, ? extends Object> getSQL,
//			Function<Void, Boolean> getCacheQueries) {
//		// TODO Auto-generated method stub
//		return super.searchEntityByPage(p, (t)->{
//			Example example = Example.create(t.getEntity()).enableLike(
//					MatchMode.START);
//			Criteria criteria = getHibernateTemplate().getSessionFactory()
//					.getCurrentSession()
//					.createCriteria(getClazz());
//			criteria.add(example);
//			criteria.addOrder(Order.asc("sequence"));
//			return criteria;
//		}, getCacheQueries);
//	}
////
//	public List<MallGoods> search(SearchParameter<MallGoods> p,
//			Function<SearchParameter<MallGoods>, ? extends Object> getSQL,Integer startStock,Integer endStock,
//			Function<Void, Boolean> getCacheQueries) {
//		// TODO Auto-generated method stub
//		return super.searchEntityByPage(p, (t)->{
//			Example example = Example.create(t.getEntity()).enableLike(
//					MatchMode.START);
//			Criteria criteria = getCurrentSession()
//					.createCriteria(MallGoods.class);
//			criteria.add(example);
//			Integer tempStartStock=startStock,tempEndStock=endStock;
//			if(startStock==null){
//				tempStartStock=-99999999;
//			}
//			if(endStock==null){
//				tempEndStock=999999999;
//			}
//			criteria.add(Restrictions.between("stockQuantity", tempStartStock, tempEndStock));
//			return criteria;
//		}, getCacheQueries);
//	}

	public void save(MallGoodsType transientInstance) {
		log.debug("saving MallGoods instance");
		try {
			transientInstance.setUpdateTime(new Date());
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}


	public void delete(MallGoodsType persistentInstance) {
		log.debug("deleting MallGoods instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MallGoodsType findById(Integer id) {
		log.debug("getting MallGoods instance with id: " + id);
		try {
			MallGoodsType instance = getCurrentSession().get(
					MallGoodsType.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	public List<MallGoodsType> findAllByParent(Integer parent) {
		log.debug("finding all MallGoods instances");
		Criteria criteria =getCurrentSession().createCriteria(MallGoodsType.class);
		if(parent!=null){
            criteria.add(Restrictions.eq("parentType.id",parent));
        }else {
        criteria.add(Restrictions.eq("type",0));
        }
//		try {
////			String queryString = "from MallGoodsType mt where mt.parentType.id = ?";
////			Query queryObject = getCurrentSession().createQuery(queryString);
////            queryObject.
////			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find all failed", re);
//			throw re;
//		}
        return criteria.list();
	}

    public List<MallGoodsType> findAll(Integer type) {
        log.debug("finding all MallGoods instances");
		try {
			String queryString = "from MallGoodsType mt where mt.type = ? order by mt.orderIndex";
			Query queryObject = getCurrentSession().createQuery(queryString);
            queryObject.setParameter(0,type);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }

	public MallGoodsType merge(MallGoodsType detachedInstance) {
		log.debug("merging MallGoods instance");
		try {
			detachedInstance.setUpdateTime(new Date());
			MallGoodsType result = (MallGoodsType) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

}