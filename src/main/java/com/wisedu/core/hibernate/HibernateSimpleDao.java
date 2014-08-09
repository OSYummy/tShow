package com.wisedu.core.hibernate;

import com.wisedu.core.page.Pagination;
import com.wisedu.core.utils.BeanUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class HibernateSimpleDao {
    private final static Logger log= LoggerFactory.getLogger(HibernateSimpleDao.class);

    @Autowired
    protected SessionFactory sessionFactory;

    protected List find(String hql, Object... values){
        return createQuery(hql, values).list();
    }

    protected Object findUnique(String hql, Object... values){
        return createQuery(hql, values).uniqueResult();
    }

    protected Query createQuery(String queryStr, Object... values){
        Query query=getSession().createQuery(queryStr);

        if(values == null) return null;

        for (int i=0; i<values.length; i++)
            query.setParameter(i, values[i]);

        return query;
    }

    /**
     * 获取分页数据
     * @param finder
     * @param pageNo
     * @param pageSize
     * @return
     */
    protected Pagination find(Finder finder, int pageNo, int pageSize){
        int totalSize=countQueryResult(finder);
        Pagination pagination=new Pagination(totalSize, pageSize, pageNo);

        if (totalSize<1){
            pagination.setList(new ArrayList<Object>());
            return pagination;
        }

        Query query=finder.createQuery(getSession());
        query.setFirstResult(pagination.getFirstResult());
        query.setMaxResults(pagination.getPageSize());

        pagination.setList(query.list());

        return pagination;
    }

    protected int countQueryResult(Finder finder){
        Query query=getSession().createQuery(finder.getRowCountHQL());

        finder.setParamsToQuery(query);
        finder.setCacheable(finder.isCacheable());

        return ((Number)query.iterate().next()).intValue();
    }

    protected List find(Finder finder){
        Query query=finder.createQuery(getSession());
        return query.list();
    }

    protected Pagination findByCriteria(Criteria criteria, int pageNo, int pageSize){
        CriteriaImpl criteria1=(CriteriaImpl)criteria;
        Projection projection=criteria1.getProjection();
        ResultTransformer transformer=criteria1.getResultTransformer();
        List<CriteriaImpl.OrderEntry> orderEntries;

        try {
            orderEntries=(List) BeanUtil.getFieldValue(criteria1, "orderEntries");
            BeanUtil.setFieldValue(criteria1, "orderEntries", new ArrayList());
        } catch (Exception e){
            throw new RuntimeException("cannot read/write 'orderEntries' from CriteriaImpl", e);
        }

        int totalSize=((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();

        Pagination pagination=new Pagination(totalSize, pageSize, pageNo);

        if(totalSize < 1){
            pagination.setList(new ArrayList<Object>());
            return pagination;
        }

        criteria.setProjection(projection);
        if (projection == null)
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

        if (transformer != null)
            criteria.setResultTransformer(transformer);

        try {
            BeanUtil.setFieldValue(criteria1, "orderEntries", orderEntries);
        } catch (Exception e) {
            throw new RuntimeException("set 'orderEntries' to CriteriaImpl faild", e);
        }

        criteria.setFirstResult(pagination.getFirstResult());
        criteria.setMaxResults(pagination.getPageSize());
        pagination.setList(criteria.list());

        return pagination;
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}