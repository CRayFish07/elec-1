package cn.yangml.elec.web.action;


import java.util.List;

import cn.yangml.elec.service.IElecCommonMsgService;
import cn.yangml.elec.util.container.ServiceProvider;
import cn.yangml.elec.web.form.ElecCommonMsgForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecCommonMsgAction extends BaseAction implements ModelDriven<ElecCommonMsgForm>{
	IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService)ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);
	private ElecCommonMsgForm elecCommonMsgForm = new ElecCommonMsgForm();
	public ElecCommonMsgForm getModel() {
		// TODO Auto-generated method stub
		return elecCommonMsgForm;
	}
	/**  
	* @Name: home
	* @Description: 查询所有的代办事宜列表
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-28 （创建日期）
	* @Parameters: 
	* @Return: String home 跳转到actiingIndex.jsp
	*/
	public String home(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgList();
		request.setAttribute("commList", list);
		return "home";
	}
	/**  
	* @Name: home
	* @Description: 保存代办事宜信息
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-29 （创建日期）
	* @Parameters: 
	* @Return: String save 重定向到actiingIndex.jsp
	*/
	public String save(){
		System.out.println("save");
		elecCommonMsgService.saveElecCommonMsg(elecCommonMsgForm);
		return "save";
	}
}
