package com.Kimalu.drp.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

public class BaseDao<T> {
	@Autowired
	protected SessionFactory sf;
	@Autowired
	protected HibernateTemplate hibernateTemplate;

	protected Class<T> entityClass;

	public BaseDao(){
		 Type genType = getClass().getGenericSuperclass();
	        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
	        entityClass = (Class) params[0];
	}
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Session getSession() {
		return sf.getCurrentSession();
	}

	public void save(T t) {
		hibernateTemplate.save(t);
	}

	public void del(T t) {
		hibernateTemplate.delete(t);
	}

	public T getEntityById(Serializable id) {
		return hibernateTemplate.get(entityClass, id);
	}

	public void update(T t) {
		hibernateTemplate.update(t);
	}
	public void evict(T t){
		this.getSession().evict(t);
	}

	private void applyNamedParameterToQuery(Query queryObject,
			String paramName, Object value) throws HibernateException {
		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection) value);
		} else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		} else {
			queryObject.setParameter(paramName, value);
		}
	}
	public  Query createQuery(final String queryString, Map<String, Object> namedParamMap) {
        Assert.hasText(queryString, "queryString²»ÄÜÎª¿Õ");

        Query query = getSession().createQuery(queryString);
        if (namedParamMap != null) {
            for (String key : namedParamMap.keySet()) {
                applyNamedParameterToQuery(query, key, namedParamMap.get(key));
            }
        }
        return query;
    }
}
