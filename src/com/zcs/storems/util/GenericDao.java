package com.zcs.storems.util;

import java.io.Serializable;
import java.util.List;

/**
 * 接口内的方法默认修饰为: public abstract
 * 
 * 接口内的属性:public static final
 */
public interface GenericDao<T, PK extends Serializable> {

	// 传入一个id属性,根据id获取对应实体中的一条记录,用get方法实现
	public T findById(PK id);

	// 传入一个id属性,根据id获取对应的动态代理对象,用load方法实现
	public T loadById(PK id);

	// 查出所有记录
	public List<T> findAll();

	// 根据传入的hql语句,查询出对应的记录
	public List<T> findByHql(String hql);

	// 根据传入的hql语句,对指定hql进行统计操作
	public Object countByHql(final String hql, final Object[] params);

	/**
	 * @param type 指定查询类型
	 * @param hql 接收上层传入的一条hql语句
	 * @param params 如果hql语句需要指定参数,放入此数组
	 * @return
	 */
	public Object findByHql(final ResultType type, final String hql,
			final Object[] params);

	/**
	 * 持久化一条记录
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity);

	/**
	 * 更新记录
	 * 
	 * @param entity
	 */
	public void update(T entity);

	// 删除记录
	public void delete(T entity);

	// 根据id删除记录,内部调用delete()方法实现删除
	public void deleteById(PK id);

	// 根据传入的hql语句对记录进行更新操作
	public void modifyByHql(String hql);

	// 根据传入的hql语句对记录进行更新操作
	public void modifyByHql(String hql, Object[] params);

	/**
	 * @param hql
	 * @param params
	 * @param pagination
	 * @return
	 */
	public Pagination getPagination(final String hql, final Object[] params,
			final Pagination pagination);
}
