package com.zcs.storems.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings("unchecked")
public class GenericDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK> {

	private Class<T> cls;

	public GenericDaoImpl() {
		cls = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public List<T> findAll() {
		return getHibernateTemplate().find("from " + cls.getName());
	}

	public T findById(Serializable id) {
		return (T) getHibernateTemplate().get(cls, id);
	}

	public T save(T entity) {
		getHibernateTemplate().save(entity);
		return entity;
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	public void deleteById(Serializable id) {
		System.out.println("id:" + id);
		T t = (T) getHibernateTemplate().get(cls, id);
		getHibernateTemplate().delete(t);
	}

	public void modifyByHql(String hql) {
		System.out.println(hql);
		Session session = getSession();
		Query q = session.createQuery(hql);
		q.executeUpdate();
	}

	public void modifyByHql(String hql, Object[] params) {

	}

	public T loadById(Serializable id) {
		return (T) getHibernateTemplate().load(cls, id);
	}

	public List<T> findByHql(String hql) {
		List<T> list = (List<T>) getHibernateTemplate().find(hql);
		return list;
	}

	public Object findByHql(final ResultType type, final String hql, final Object[] params) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < params.length; i++) {
					System.out.println("-------params[" + i + "] = " + params[i]);
					query.setParameter(i, params[i]);
				}
				if (ResultType.NO_RESULT.equals(type)) {
					query.executeUpdate();
					return null;
				} else if (ResultType.UINQUE_RESULT.equals(type)) {
					return query.uniqueResult();
				} else if (ResultType.MULTI_RESULT.equals(type)) {
					return query.list();
				} else {
					return null;
				}
			}
		});
	}

	public Pagination getPagination(final String hql, final Object[] params, final Pagination pagination) {
		return (Pagination) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				query.setMaxResults(pagination.getPageSize());
				query.setFirstResult(pagination.getFirstResult());
				pagination.setList(query.list());
				return pagination;
			}
		});
	}

	// 根据传入的hql语句,对指定hql进行统计操作
	public Object countByHql(final String hql, final Object[] params) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.uniqueResult();
			}
		});
	}
}
