package cn.yangml.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.yangml.elec.dao.IElecUserDao;
import cn.yangml.elec.domain.ElecUser;
@Repository(IElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements IElecUserDao{
	/**  
	* @Name: findElecPopedomByLogonName
	* @Description: 使用登录名获取当前登录名所具有的权限，查询数据库表
	*                                                elec_user
	*                                                elec_user_role
	*                                                elec_role_popedom
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-10 （创建日期）
	* @Parameters: String name 登录名
	* @Return: List<Object> 存放该用户具有的权限集合
	*/
	public List<Object> findElecPopedomByLogonName(final String name) {
		final String sql = "SELECT a.popedomcode as popedom FROM elec_role_popedom a " +
					 "LEFT OUTER JOIN elec_user_role b ON a.RoleID = b.RoleID " +
					 "INNER JOIN elec_user c ON b.UserID = c.UserID " +
					 "AND c.logonName = ? " +
					 "WHERE c.isDuty = '1'";
		List<Object> list = (List<Object>)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql)
				                     .addScalar("popedom",Hibernate.STRING);
				query.setParameter(0, name);
				return query.list();
			}
			
		});
		return list;
	}
	/**  
	* @Name: findElecRoleByLogonName
	* @Description: 使用登录名获取当前登录名所具有的角色，查询数据库表
	*                                                elec_user
	*                                                elec_user_role
	*                                                elec_systemddl
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-10 （创建日期）
	* @Parameters: String name 登录名
	* @Return: List<Object[]> 存放该用户具有的角色集合
	*/
		public List<Object[]> findElecRoleByLogonName(final String name) {
					// TODO Auto-generated method stub
					final String sql = "SELECT b.ddlCode as code,b.ddlName as name FROM elec_user_role a " +
					 "LEFT OUTER JOIN elec_systemddl b ON a.RoleID = b.ddlCode " +
					 "AND b.keyword = '角色类型' " +
					 "INNER JOIN elec_user c ON a.UserID = c.UserID " +
					 "AND c.logonName = ? " +		
					 "WHERE c.isDuty = '1'";
			List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback(){
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql)
				                     .addScalar("code",Hibernate.STRING)
				                     .addScalar("name",Hibernate.STRING);
				query.setParameter(0, name);
				return query.list();
			}
			
			});
			return list;
		}

}
