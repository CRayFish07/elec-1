package cn.yangml.elec.web.action;

import java.util.Hashtable;
import java.util.List;

import cn.yangml.elec.domain.ElecUser;
import cn.yangml.elec.service.IElecCommonMsgService;
import cn.yangml.elec.service.IElecUserService;
import cn.yangml.elec.util.MD5keyBean;
import cn.yangml.elec.util.container.ServiceProvider;
import cn.yangml.elec.web.form.ElecCommonMsgForm;
import cn.yangml.elec.web.form.ElecMenuForm;

import com.opensymphony.xwork2.ModelDriven;


@SuppressWarnings("serial")
public class ElecMenuAction extends BaseAction implements ModelDriven<ElecMenuForm>{
	
	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService)ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);
	private IElecUserService elecUserService = (IElecUserService)ServiceProvider.getService(IElecUserService.SERVICE_NAME);
	private ElecMenuForm elecMenuForm = new ElecMenuForm();
	public ElecMenuForm getModel() {
		// TODO Auto-generated method stub
		return elecMenuForm;
	}
	/**  
	* @Name: home
	* @Description: 从登陆页面获取登陆名和密码，验证是否合法
	*               如果合法：则验证成功，跳转到home.jsp
	*               如果不合法：则验证失败，回退到index.jsp
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-10 （创建日期）
	* @Parameters: 无
	* @Return: String home 
	*               验证成功，跳转到home.jsp
	*               验证失败，回退到index.jsp
	*/
	public String home(){
		//获取当前登录名和密码
		String name = elecMenuForm.getName();
		String password = elecMenuForm.getPassword();
		MD5keyBean md5 = new MD5keyBean();
		String md5password = md5.getkeyBeanofStr(password);
		//使用登录名查询数据库，获取用户的详细信息
		ElecUser elecUser = elecUserService.findElecUserByLogonName(name);
		if(elecUser==null){
			this.addFieldError("error", "您当前输入的登录名不存在");
			return "error";
		}
		if(password==null || password.equals("") || !elecUser.getLogonPwd().equals(md5password)){
			this.addFieldError("error", "您当前输入的密码有误或不存在");
			return "error";
		}
		
		request.getSession().setAttribute("globle_user", elecUser);
		
		//获取当前登录名所具有的权限
		String popedom = elecUserService.findElecPopedomByLogonName(name);
		if(popedom==null || "".equals(popedom)){
			this.addFieldError("error", "当前登录名没有分配权限，请与管理员联系");
			return "error";
		}
		request.getSession().setAttribute("globle_popedom", popedom);
		//获取当前登录名所具有的角色
		Hashtable<String, String> ht = elecUserService.findElecRoleByLogonName(name);
		if(ht==null){
			this.addFieldError("error", "当前登录名没有分配角色，请与管理员联系");
			return "error";
		}
		request.getSession().setAttribute("globle_role", ht);
		return "home";
	}
	public String title(){
		return "title";
	}
	public String left(){
		return "left";
	}
	public String change1(){
		return "change1";
	}
	public String alermJX(){
		return "alermJX";
	}
	public String alermXZ(){
		return "alermXZ";
	}
	public String alermSB(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermSB";
	}
	public String alermYS(){
		return "alermYS";
	}
	/**  
	* @Name: alermZD
	* @Description: 查询当天的站点运行情况 
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-29 （创建日期）
	* @Parameters: 无
	* @Return: String alermZD 跳转到alermZD.jsp
	*/
	public String alermZD(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermZD";
	}
	/**  
	* @Name: logout
	* @Description: 重新回退到登录页面
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-03-10 （创建日期）
	* @Parameters: 无
	* @Return: String logout 跳转到index.jsp
	*/
	public String logout(){
		//清空session
		request.getSession().invalidate();
		return "logout";
	}
	public String loading(){
		return "loading";
	}
}
