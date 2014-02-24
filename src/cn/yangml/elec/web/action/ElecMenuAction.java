package cn.yangml.elec.web.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.yangml.elec.service.IElecCommonMsgService;
import cn.yangml.elec.util.container.ServiceProvider;
import cn.yangml.elec.web.form.ElecCommonMsgForm;
import cn.yangml.elec.web.form.ElecMenuForm;


@SuppressWarnings("serial")
public class ElecMenuAction extends BaseAction implements ModelDriven<ElecMenuForm>{
	
	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService)ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);
	private ElecMenuForm elecMenuForm = new ElecMenuForm();
	public ElecMenuForm getModel() {
		// TODO Auto-generated method stub
		return elecMenuForm;
	}
	public String home(){
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
	public String loading(){
		return "loading";
	}
}
