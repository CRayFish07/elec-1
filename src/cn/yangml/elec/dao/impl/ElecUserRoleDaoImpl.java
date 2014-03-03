package cn.yangml.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.yangml.elec.dao.IElecUserRoleDao;
import cn.yangml.elec.domain.ElecUserRole;

@Repository(IElecUserRoleDao.SERVICE_NAME)
public class ElecUserRoleDaoImpl extends CommonDaoImpl<ElecUserRole> implements IElecUserRoleDao {

	/**  
	* @Name: findElecUserListByRoleID
	* @Description: 查询用户列表集合，获取系统中所有的用户，并与该角色拥有的用户进行匹配
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-03 （创建日期）
	* @Parameters: String roleID 角色ID
	* @Return: List<Object[]> 用户集合（匹配完成）
	*/
	public List<Object[]> findElecUserListByRoleID(final String roleID) {
		final String sql = "SELECT DISTINCT CASE elec_user_role.RoleID " +
					 "WHEN ? THEN '1' ELSE '0' END AS flag, " +
					 "elec_user.UserID as userID, " +
					 "elec_user.UserName as userName, " +
					 "elec_user.LogonName as logonName " +
					 "FROM elec_user " + 
					 "LEFT OUTER JOIN elec_user_role " + 
					 "ON elec_user.UserID = elec_user_role.UserID " + 
					 "AND elec_user_role.RoleID = ? " +
					 "WHERE elec_user.IsDuty='1'";
		List<Object []> list = (List<Object []>) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql)
				              .addScalar("flag", Hibernate.STRING)
				              .addScalar("userID", Hibernate.STRING)
				              .addScalar("userName", Hibernate.STRING)
				              .addScalar("logonName", Hibernate.STRING);
				query.setString(0, roleID);
				query.setString(1, roleID);
				return query.list();
			}
		});
		return list;
	}

	
	
}
