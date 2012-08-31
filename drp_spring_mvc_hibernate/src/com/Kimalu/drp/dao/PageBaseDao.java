package com.Kimalu.drp.dao;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.util.Assert;

public  class  PageBaseDao<T> extends BaseDao<T> {
	
	public Page<T> getAll(Page<T> page){
		return getByCriteria(page);
	}
	public Page<T> getByCriteria(Page<T> page,Criterion... criterions){
		Assert.notNull(page, "page不能为空");
		Criteria c=createCriteria(criterions);
		page.setTotalNum(c.list().size());
		setPageParam(c,page);
//		page.setTotalNum(this.getCount(entityClass.getSimpleName()));
		page.setList(c.list());
		return page;
	}
	
	public Page<T> getByQuery(Page<T> page,String hql,  Map<String, Object> paraMap) {
		Assert.notNull(page, "page不能为空");
		Query q = null;
		if(paraMap!=null){
			 q = createQuery(hql, paraMap);
		}else{
			 q =getSession().createQuery(hql);
		}
		page.setTotalNum(q.list().size());
		setPageParam(q, page);
//		page.setTotalNum(this.getCount(entityClass.getSimpleName()));
		page.setList(q.list());
		return page;
	}
	private Query setPageParam(Query q,Page<T> page) {
		q.setFirstResult(page.getFirst());
		q.setMaxResults(page.getPageSize());
		return q;
	}
	
	private Criteria setPageParam(Criteria c, Page<T> page) {
		c.setFirstResult(page.getFirst());
		c.setMaxResults(page.getPageSize());
		return c;
	}
	private Criteria createCriteria(Criterion[] criterions) {
		Criteria criteria=this.getSession().createCriteria(entityClass);
		for(Criterion c:criterions){
			criteria.add(c);
		}
		return criteria;
	}


}
