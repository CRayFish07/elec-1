package cn.yangml.elec.dao.impl;


import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.yangml.elec.dao.IElecCommonMsgDao;
import cn.yangml.elec.domain.ElecCommonMsg;
@Repository(IElecCommonMsgDao.SERVICE_NAME)
public class ElecCommonMsgDaoImpl extends CommonDaoImpl<ElecCommonMsg> implements IElecCommonMsgDao {
	/**  
	* @Name: findElecCommonMsgListByCurrentDate
	* @Description: 通过当前日期查询代办事宜列表
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-29 （创建日期）
	* @Parameters: String currentDate 当前日期
	* @Return: List<Object[]> 代办事宜列表
	*/
	@SuppressWarnings("unchecked")
	public List<Object[]> findElecCommonMsgListByCurrentDate(String currentDate) {
		// TODO Auto-generated method stub
		final String sql = "SELECT o.StationRun as stationRun,o.DevRun as devRun " +
	     "FROM Elec_CommonMsg o " + 
        "WHERE o.CreateDate = '" + currentDate + "'";
		List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback() {
			@SuppressWarnings("deprecation")
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql)
							  .addScalar("stationRun",Hibernate.STRING)
				              .addScalar("devRun",Hibernate.STRING);
				return query.list();
			}
		});
		return list;
	}

}
