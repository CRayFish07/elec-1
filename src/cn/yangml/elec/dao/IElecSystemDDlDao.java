package cn.yangml.elec.dao;


import java.util.List;

import cn.yangml.elec.domain.ElecSystemDDl;

public interface IElecSystemDDlDao extends ICommonDao<ElecSystemDDl> {
	public final static String SERVICE_NAME="cn.yangml.elec.dao.impl.ElecSystemDDlDaoImpl";

	List<Object> findKeyWord();

	String findDDlName(String ddlCode, String keyword);

}
