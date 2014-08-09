package com.wisedu.core.hibernate;

import com.wisedu.core.utils.BeanUtil;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;

import java.io.Serializable;
import java.util.List;

public abstract class HibernateBaseDao<T, ID extends Serializable> extends HibernateSimpleDao{
    protected T get(ID id){
        return get(id, false);
    }

    protected T get(ID id, boolean lock){
        T entity;
        if(lock){
            entity = (T)getSession().get(getEntityClass(), id, LockMode.UPGRADE);
        } else{
            entity = (T)getSession().get(getEntityClass(), id);
        }

        return entity;
    }

    /*获取实例的类*/
    abstract protected Class<T> getEntityClass();

    protected List<T> findByProperty(String property, Object value){
        return createCriteria(Restrictions.eq(property, value)).list();
    }

    protected T findUniqueByProperty(String property, Object value){
        return (T)createCriteria(Restrictions.eq(property, value)).uniqueResult();
    }

    protected int countByProperty(String property, Object value){
        return ((Number)createCriteria(Restrictions.eq(property, value)).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    protected List findByCriteria(Criterion... criterions){
        return createCriteria(criterions).list();
    }

    /*public T updateByUpdater(Updater<T> updater){
        ClassMetadata classMetadata=sessionFactory.getClassMetadata(getEntityClass());
        T bean=updater.getBean();
        T po=(T)getSession().get(getEntityClass(), classMetadata.getIdentifier(bean, EntityMode.POJO));
        updaterCopyToPersistentObject(updater, po, classMetadata);
        return po;
    }

    protected void updaterCopyToPersistentObject(Updater<T> updater, T po, ClassMetadata classMetadata){
        String[] propNames=classMetadata.getPropertyNames();
        String identifierName=classMetadata.getIdentifierPropertyName();
        T bean=updater.getBean();
        Object value;

        for (String propName: propNames){
            if (propName.equals(identifierName)) continue;
            try {
                value= BeanUtil.getSimpleProperty(bean, propName);
                if (!updater.isUpdate(propName, value)) continue;
                classMetadata.setPropertyValue(po, propName, value, EntityMode.POJO);
            } catch (Exception e) {
                throw new RuntimeException("copy property to persistent object failed: '"+ propName + "'", e);
            }
        }
    }*/

    protected Criteria createCriteria(Criterion... criterions){
        Criteria criteria=getSession().createCriteria(getEntityClass());
        for (Criterion criterion: criterions){
            criteria.add(criterion);
        }
        return criteria;
    }
}