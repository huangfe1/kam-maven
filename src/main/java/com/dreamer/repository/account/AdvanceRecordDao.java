package com.dreamer.repository.account;

import com.dreamer.domain.account.AdvanceRecord;
import com.dreamer.domain.account.VoucherRecord;
import com.dreamer.domain.user.Agent;
import com.dreamer.domain.user.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ps.mx.otter.utils.SearchParameter;
import ps.mx.otter.utils.dao.hibernate.HibernateBaseDAO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Created by huangfei on 16/4/11.
 */
@Repository("advanceRecordDao")
public class AdvanceRecordDao extends HibernateBaseDAO<AdvanceRecord> {
    /**
     * 置换券分页查询
     * @param p
     * @param getCacheQueries
     * @return
     */

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    public List<AdvanceRecord> searchEntityByPage(SearchParameter<AdvanceRecord> p, User user) {
        return super.searchEntityByPage(
                p,
                (t) -> {
                    Example example = Example.create(t.getEntity()).enableLike(
                            MatchMode.ANYWHERE);
                    Criteria criteria = getCurrentSession()
                            .createCriteria(AdvanceRecord.class);
                    criteria.add(example);
                    criteria.addOrder(Order.desc("updateTime"));//时间排序
                    criteria.addOrder(Order.asc("type"));//积分量排序
                    criteria.addOrder(Order.asc("advance"));//积分量排序
                    Criteria cr= criteria.createCriteria("agent");
                    if(user!=null){
                        cr.add(Restrictions.eq("id",user.getId()));
                    }
                    if(t.getEntity().getAgent()!=null&&!t.getEntity().getAgent().getRealName().equals("")){
                        cr.add(Restrictions.eq("realName",t.getEntity().getAgent().getRealName()));
                    }
                    return criteria;
                }, null);
    }

    /**
     * 保存实体
     * @param advanceRecord
     */
    public void save(AdvanceRecord advanceRecord){
        try {
            advanceRecord.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            getCurrentSession().save(advanceRecord);
        }catch (RuntimeException e){
            throw  e;
        }
    }

    /**
     * 与updateOrSave的区别是,如果一个事务中取出来实体  没有保存然后关闭session   然后再另外一个事务中又取出了一个实体  然后保存 update就会报错  merge不会
     * @param advanceRecord
     */
    public void merge(AdvanceRecord advanceRecord){
        try {
            advanceRecord.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            getCurrentSession().merge(advanceRecord);
        }catch (RuntimeException e){
            throw  e;
        }

    }

    /**
     * 根据实例查询
     * @param advanceRecord
     * @return
     */
//    public List<AdvanceRecord> findByExample(AdvanceRecord advanceRecord){
//        return getCurrentSession().createCriteria(AdvanceRecord.class).add(Example.create(advanceRecord)).list();
//    }




}
