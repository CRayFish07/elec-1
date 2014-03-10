package cn.yangml.elec.web.action;

import java.util.List;

import cn.yangml.elec.service.IElecRoleService;
import cn.yangml.elec.service.IElecSystemDDlService;
import cn.yangml.elec.util.XmlObject;
import cn.yangml.elec.util.container.ServiceProvider;
import cn.yangml.elec.web.form.ElecRoleForm;
import cn.yangml.elec.web.form.ElecSystemDDlForm;
import cn.yangml.elec.web.form.ElecUserForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecRoleAction extends BaseAction implements ModelDriven<ElecRoleForm>{
	private IElecRoleService elecRoleService = (IElecRoleService)ServiceProvider.getService(IElecRoleService.SERVICE_NAME);
	
	private IElecSystemDDlService elecSystemDDlService = (IElecSystemDDlService)ServiceProvider.getService(IElecSystemDDlService.SERVICE_NAME);
	
	private ElecRoleForm elecRoleForm = new ElecRoleForm();
	public ElecRoleForm getModel() {
		// TODO Auto-generated method stub
		return elecRoleForm;
	}
	/**  
	* @Name: home
	* @Description: 查询所有的角色类型（在数据字典表中获取）
	*               从Function.xml文件中查询系统所有的功能权限
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-27 （创建日期）
	* @Parameters: 无
	* @Return: String home 跳转到roleIndex.jsp
	*/
	public String home(){
		//获取所有的角色类型
		List<ElecSystemDDlForm> systemList = elecSystemDDlService.findElecSystemDDlListByKeyword("角色类型");
		request.setAttribute("systemList", systemList);
		//从Function.xml配置文件中获取权限的集合
		List<XmlObject> xmlList = elecRoleService.readXml();
		request.setAttribute("xmlList", xmlList);
		return "home";
	}
	/**  
	* @Name: edit
	* @Description: 1、使用角色ID查询该角色下具有的权限，并与系统中所有的权限进行匹配
	*               2、使用角色ID查询该角色所拥有的用户
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-03 （创建日期）
	* @Parameters: 无
	* @Return: String edit 跳转到roleEdit.jsp
	*/
	public String edit(){
		String roleID = elecRoleForm.getRole();
		//查询权限集合
		List<XmlObject> xmlList = elecRoleService.readEditXml(roleID);
		request.setAttribute("xmlList", xmlList);
		//查询用户集合
		List<ElecUserForm> userList = elecRoleService.findElecUserListByRoleID(roleID);
		request.setAttribute("userList", userList);
		return "edit";
	}
	/**  
	* @Name: save
	* @Description: 执行保存，保存角色和权限的关联表
	*                        保存用户和角色的关联表
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-03 （创建日期）
	* @Parameters: 无
	* @Return: String save 重定向到roleIndex.jsp
	*/
	public String save(){
		elecRoleService.saveRole(elecRoleForm);
		return "save";
	}

}
