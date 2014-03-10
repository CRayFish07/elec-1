package cn.yangml.elec.dao;

import java.util.List;

import cn.yangml.elec.domain.ElecUser;

public interface IElecUserDao extends ICommonDao<ElecUser>{
	public final static String SERVICE_NAME = "cn.yangml.elec.dao.impl.ElecUserDaoImpl";
	List<Object> findElecPopedomByLogonName(String name);
	List<Object[]> findElecRoleByLogonName(String name);


}
