package cn.yangml.elec.dao;

import java.util.List;

import cn.yangml.elec.domain.ElecCommonMsg;

public interface IElecCommonMsgDao extends ICommonDao<ElecCommonMsg> {
	public final static String SERVICE_NAME="cn.yangml.elec.dao.impl.ElecCommonMsgDaoImpl";

	List<Object[]> findElecCommonMsgListByCurrentDate(String currentDate);





	

	
}
