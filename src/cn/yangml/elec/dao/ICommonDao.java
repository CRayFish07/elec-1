package cn.yangml.elec.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import cn.yangml.elec.domain.ElecSystemDDl;



public interface ICommonDao<T> {
	public void save(T entity);
	public void update(T elText);
	public T findObjectById(Serializable id);
	void deleteObjectByIds(Serializable ... ids);
	void deleteObjectByCollection(Collection<T> entities);
	List<T> findCollectionByConditionNoPage(String hqlWhere,
			Object[] params, LinkedHashMap<String, String> orderby);
	void saveObjectByCollection(Collection<T> entities);
	
}
