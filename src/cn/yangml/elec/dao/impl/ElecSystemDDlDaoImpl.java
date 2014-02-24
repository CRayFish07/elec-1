package cn.yangml.elec.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yangml.elec.dao.IElecSystemDDlDao;
import cn.yangml.elec.domain.ElecSystemDDl;
@Repository(IElecSystemDDlDao.SERVICE_NAME)
public class ElecSystemDDlDaoImpl extends CommonDaoImpl<ElecSystemDDl> implements IElecSystemDDlDao {
	/**  
	* @Name: findKeyWord
	* @Description: 查询数据类型关键字，且去掉重复值
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-01-07 （创建日期）
	* @Parameters: 无
	* @Return: List<ElecSystemDDl> 数据类型列表
	*/
	@SuppressWarnings("unchecked")
	public List<Object> findKeyWord() {
		// TODO Auto-generated method stub
		String hql = "select distinct o.keyword from ElecSystemDDl o";
		List<Object> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	/**  
	* @Name: findDDlName
	* @Description: 用数据类型和数据项的编号获取数据项的名称
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: String ddlCode 数据项编号
	*              String keyword 数据类型
	* @Return: String  数据项名称
	*/
	public String findDDlName(String ddlCode, String keyword) {
		// TODO Auto-generated method stub
		String hql = "from ElecSystemDDl where keyword = '"+keyword+"'"+
		" and ddlCode=" + ddlCode;
		List<ElecSystemDDl> list = this.getHibernateTemplate().find(hql);
		String ddlName = "";
		if(list!=null && list.size()>0){
			ElecSystemDDl elecSystemDDl =list.get(0);
			ddlName = elecSystemDDl.getDdlName();
		}
		return ddlName;
	}


}
