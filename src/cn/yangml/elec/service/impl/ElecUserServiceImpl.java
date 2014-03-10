package cn.yangml.elec.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.yangml.elec.dao.IElecSystemDDlDao;
import cn.yangml.elec.dao.IElecUserDao;
import cn.yangml.elec.domain.ElecUser;
import cn.yangml.elec.service.IElecUserService;
import cn.yangml.elec.util.StringHelper;
import cn.yangml.elec.web.form.ElecUserForm;

@Transactional(readOnly=true)
@Service(IElecUserService.SERVICE_NAME)
public class ElecUserServiceImpl implements IElecUserService{
	
	@Resource(name=IElecUserDao.SERVICE_NAME)
	private IElecUserDao elecUserDao;

	@Resource(name=IElecSystemDDlDao.SERVICE_NAME)
	private IElecSystemDDlDao elecSystemDDlDao;
	
	/**  
	* @Name: findElecUserList
	* @Description: 查询用户列表信息，
	*               判断用户姓名是否为空，如果不为空按照用户姓名组织查询条件
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: ElecUserForm elecUserForm VO对象存放用户姓名
	* @Return: List<ElecUserForm> 用户信息结果集对象
	*/
	public List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm) {
		// 组织查询条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecUserForm!=null && elecUserForm.getUserName()!=null && !elecUserForm.getUserName().equals("")){
			hqlWhere += " and o.userName like ?";
			paramsList.add("%"+elecUserForm.getUserName()+"%");
		}
		Object [] params = paramsList.toArray();
		//组织排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "desc");
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<ElecUserForm> formList = this.elecUserPOListToVOList(list);
		return formList;
	}
	/**  
	* @Name: elecUserPOListToVOList
	* @Description: 获取的用户列表中的值从PO对象转换成VO对象
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: List<ElecUser> list 存放PO对象
	* @Return: List<ElecUserForm> 存放VO对象
	*/
	private List<ElecUserForm> elecUserPOListToVOList(List<ElecUser> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		ElecUserForm elecUserForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			ElecUser elecUser = list.get(i);
			elecUserForm = new ElecUserForm();
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setLogonName(elecUser.getLogonName());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setSexID(elecUser.getSexID()!=null && !elecUser.getSexID().equals("")?elecSystemDDlDao.findDDlName(elecUser.getSexID(),"性别"):"");
			elecUserForm.setContactTel(elecUser.getContactTel());
			elecUserForm.setIsDuty(elecUser.getIsDuty()!=null && !elecUser.getIsDuty().equals("")?elecSystemDDlDao.findDDlName(elecUser.getIsDuty(),"是否在职"):"");
			formList.add(elecUserForm);
		}
		return formList;
	}
	/**  
	* @Name: saveElecUser
	* @Description: 保存用户信息
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: ElecUserForm elecUserForm VO对象用于存放用户信息
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecUser(ElecUserForm elecUserForm) {
		//VO对象转换成PO对象
		//VO对象转换成PO对象
		ElecUser elecUser = this.elecUserVOToPO(elecUserForm);
		if(elecUserForm.getUserID()!=null && !elecUserForm.getUserID().equals("")){
			elecUserDao.update(elecUser);
		}
		else{
			elecUserDao.save(elecUser);
		}
	}
	/**  
	* @Name: elecUserVOToPO
	* @Description: 将用户信息从VO对象转换PO对象
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-26 （创建日期）
	* @Parameters: ElecUserForm elecUserForm VO对象用于存放用户信息
	* @Return: ElecUser PO对象，用于保存用户信息
	*/
	private ElecUser elecUserVOToPO(ElecUserForm elecUserForm) {
		ElecUser elecUser = new ElecUser();
		if(elecUserForm!=null){
			if(elecUserForm.getUserID()!=null && !elecUserForm.getUserID().equals("")){
				elecUser.setUserID(elecUserForm.getUserID());
				if(elecUserForm.getOffDutyDate()!=null && !elecUserForm.getOffDutyDate().equals("")){
					elecUser.setOffDutyDate(StringHelper.stringConvertDate(elecUserForm.getOffDutyDate()));
				}
			}
			elecUser.setJctID(elecUserForm.getJctID());
			elecUser.setUserName(elecUserForm.getUserName());
			elecUser.setLogonName(elecUserForm.getLogonName());
			elecUser.setLogonPwd(elecUserForm.getLogonPwd());
			elecUser.setSexID(elecUserForm.getSexID());
			if(elecUserForm.getBirthday()!=null && !elecUserForm.getBirthday().equals("")){
				elecUser.setBirthday(StringHelper.stringConvertDate(elecUserForm.getBirthday()));
			}
			elecUser.setAddress(elecUserForm.getAddress());
			elecUser.setContactTel(elecUserForm.getContactTel());
			elecUser.setEmail(elecUserForm.getEmail());
			elecUser.setMobile(elecUserForm.getMobile());
			elecUser.setIsDuty(elecUserForm.getIsDuty());
			if(elecUserForm.getOnDutyDate()!=null && !elecUserForm.getOnDutyDate().equals("")){
				elecUser.setOnDutyDate(StringHelper.stringConvertDate(elecUserForm.getOnDutyDate()));
			}
			elecUser.setRemark(elecUserForm.getRemark());
		}
		return elecUser;
	}
	/**  
	* @Name: checkLogonName
	* @Description: 使用登录名作为条件查询数据库，判断当前登录名在数据库中是否存在
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: String logonName 登录名
	* @Return: String  checkflag=1：如果值为1，说明当前登录名在数据库中有重复记录，则不能进行保存
	*                  checkflag=2：如果值为2，说明当前登录名在数据库中没有重复值，可以进行保存
	*/
	public String checkLogonName(String logonName) {
		String hqlWhere = " and o.logonName = ?";
		Object [] params = {logonName};
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		String checkflag = "";
		if(list!=null && list.size()>0){
			checkflag = "1";
		}
		else{
			checkflag = "2";
		}
		return checkflag;
	}
	/**  
	* @Name: findElecUser
	* @Description: 使用用户ID进行查询，获取用户的详细信息
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: ElecUserForm elecUserForm VO对象用于存放用户ID
	* @Return: ElecUserForm VO对象存放用户的详细信息
	*/
	public ElecUserForm findElecUser(ElecUserForm elecUserForm) {
		String userID = elecUserForm.getUserID();
		ElecUser elecUser = elecUserDao.findObjectById(userID);
		//PO对象转换成VO对象
		elecUserForm = this.elecUserPOToVO(elecUser,elecUserForm);
		return elecUserForm;
	}
	/**  
	* @Name: elecUserPOToVO
	* @Description: 获取用户的详细信息，从PO对象转换成VO对象
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-26 （创建日期）
	* @Parameters: ElecUser elecUser PO对象存放用户详细信息
	* @Return: ElecUserForm VO对象存放用户的详细信息
	*/
	private ElecUserForm elecUserPOToVO(ElecUser elecUser,ElecUserForm elecUserForm) {
		//ElecUserForm elecUserForm = new ElecUserForm();
		if(elecUser!=null){
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setJctID(elecUser.getJctID());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setLogonName(elecUser.getLogonName());
			elecUserForm.setLogonPwd(elecUser.getLogonPwd());
			elecUserForm.setSexID(elecUser.getSexID());
			elecUserForm.setBirthday(String.valueOf(elecUser.getBirthday()!=null && !elecUser.getBirthday().equals("")?elecUser.getBirthday():""));
			elecUserForm.setAddress(elecUser.getAddress());
			elecUserForm.setContactTel(elecUser.getContactTel());
			elecUserForm.setEmail(elecUser.getEmail());
			elecUserForm.setMobile(elecUser.getMobile());
			elecUserForm.setIsDuty(elecUser.getIsDuty());
			elecUserForm.setOnDutyDate(String.valueOf(elecUser.getOnDutyDate()!=null && !elecUser.getOnDutyDate().equals("")?elecUser.getOnDutyDate():""));
			elecUserForm.setOffDutyDate(String.valueOf(elecUser.getOffDutyDate()!=null && !elecUser.getOffDutyDate().equals("")?elecUser.getOffDutyDate():""));
			elecUserForm.setRemark(elecUser.getRemark());
		}
		return elecUserForm;
	}
	/**  
	* @Name: deleteElecUser
	* @Description: 从页面中获取userID的值，通过userID删除用户信息
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: ElecUserForm elecUserForm VO对象存放用户ID
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteElecUser(ElecUserForm elecUserForm) {
		String userID = elecUserForm.getUserID();
		elecUserDao.deleteObjectByIds(userID);
	}
	/**  
	* @Name: findElecRoleByLogonName
	* @Description: 使用登录名获取当前登录名所具有的角色
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-10 （创建日期）
	* @Parameters: String name 登录名
	* @Return: Hashtable<String, String> 存放角色的集合
	*/
	public ElecUser findElecUserByLogonName(String name) {
		String hqlWhere = " and o.logonName = ?";
		Object [] params = {name};
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		ElecUser elecUser = null;
		if(list!=null && list.size()>0){
			elecUser = list.get(0);
		}
		return elecUser;
	}
	/**  
	* @Name: findElecPopedomByLogonName
	* @Description: 使用登录名获取当前登录名所具有的权限
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-10 （建日期）
	* @Parameters: String name 登录名
	* @Return: String 用户存放该用户具有的权限
	*/
	public String findElecPopedomByLogonName(String name) {
		List<Object> list = elecUserDao.findElecPopedomByLogonName(name);
		StringBuffer buffer = new StringBuffer("");
		for(int i=0;list!=null && i<list.size();i++){
			Object object = list.get(i);
			buffer.append(object.toString());
		}
		return buffer.toString();
	}
	/**  
	* @Name: findElecRoleByLogonName
	* @Description: 使用登录名获取当前登录名所具有的角色
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-10 （创建日期）
	* @Parameters: String name 登录名
	* @Return: Hashtable<String, String> 存放角色的集合
	*/
	public Hashtable<String, String> findElecRoleByLogonName(String name) {
		List<Object[]> list = elecUserDao.findElecRoleByLogonName(name);
		Hashtable<String, String> ht = null;
		if(list!=null && list.size()>0){
			ht = new Hashtable<String, String>();
			for(int i=0;i<list.size();i++){
				Object[] object = list.get(i);
				ht.put(object[0].toString(), object[1].toString());
			}
		}
		return ht;
	}
	}