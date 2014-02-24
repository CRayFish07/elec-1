package cn.yangml.elec.web.action;

import java.util.List;

import cn.yangml.elec.service.IElecSystemDDlService;
import cn.yangml.elec.service.IElecUserService;
import cn.yangml.elec.util.container.ServiceProvider;
import cn.yangml.elec.web.form.ElecSystemDDlForm;
import cn.yangml.elec.web.form.ElecUserForm;

import com.opensymphony.xwork2.ModelDriven;
@SuppressWarnings("serial")
public class ElecUserAction extends BaseAction implements ModelDriven<ElecUserForm>{

	private IElecUserService elecUserService = (IElecUserService)ServiceProvider.getService(IElecUserService.SERVICE_NAME);
	private IElecSystemDDlService elecSystemDDlService = (IElecSystemDDlService)ServiceProvider.getService(IElecSystemDDlService.SERVICE_NAME);
	
	private ElecUserForm elecUserForm = new ElecUserForm();
	public ElecUserForm getModel() {
		// TODO Auto-generated method stub
		return elecUserForm;
	}
	/**  
	* @Name: home
	* @Description: 查询用户列表信息
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: 无
	* @Return: String home 跳转到userIndex.jsp
	*/
	public String home(){
		List<ElecUserForm> list = elecUserService.findElecUserList(elecUserForm);
		System.out.println(list.get(0).getUserName());
		request.setAttribute("userList", list);
		return "home";
	}
	/**  
	* @Name: add
	* @Description: 跳转到添加用户的页面
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: 无
	* @Return: String add 跳转到userAdd.jsp
	*/
	public String add(){
		this.initSystemDDl();
		return "add";
	}
	/**  
	* @Name: initSystemDDl
	* @Description:初始化新增和编辑用户页面中使用的数据字典项
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: 无
	* @Return: 无
	*/
	private void initSystemDDl() {
		/**
		 * 使用数据类型进行查询，获取对应数据类型下的数据项编号和数据项名称
		 * 查询性别、所属单位、是否在职
		 */
		List<ElecSystemDDlForm> sexList = elecSystemDDlService.findElecSystemDDlListByKeyword("性别");
		List<ElecSystemDDlForm> jctList = elecSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		List<ElecSystemDDlForm> isdutyList = elecSystemDDlService.findElecSystemDDlListByKeyword("是否在职");
		request.setAttribute("sexList", sexList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("isdutyList", isdutyList);
	}
	/**  
	* @Name: save
	* @Description: 保存用户信息
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: 无
	* @Return: String save 重定向到userIndex.jsp
	*/
	public String save(){
		elecUserService.saveElecUser(elecUserForm);
		return "list";
	}
	
	/**  
	* @Name: edit
	* @Description: 跳转到编辑的页面
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: 无
	* @Return: String edit 跳转到userEdit.jsp
	*/
	public String edit(){
		elecUserForm = elecUserService.findElecUser(elecUserForm);
		//使用值栈的形式传递elecUserForm对象
		//ActionContext.getContext().getValueStack().push(elecUserForm);
		this.initSystemDDl();
		/**
		 *  使用viewflag字段
		 *  判断当前用户跳转的是编辑页面还是明细页面
		 *    * 如果viewflag==null：说明当前操作的是编辑页面
		 *    * 如果viewflag==1:说明当前操作的是明细页面
		 */
		String viewflag = elecUserForm.getViewflag();
		request.setAttribute("viewflag", viewflag);
		return "edit";
	}
	/**  
	* @Name: delete
	* @Description: 删除用户信息
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-23 （创建日期）
	* @Parameters: 无
	* @Return: String delete 跳转到userIndex.jsp
	*/
	public String delete(){
		elecUserService.deleteElecUser(elecUserForm);
		return "list";
	}
}
