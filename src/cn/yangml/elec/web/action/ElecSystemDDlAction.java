package cn.yangml.elec.web.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.yangml.elec.service.IElecCommonMsgService;
import cn.yangml.elec.service.IElecSystemDDlService;
import cn.yangml.elec.util.container.ServiceProvider;
import cn.yangml.elec.web.form.ElecCommonMsgForm;
import cn.yangml.elec.web.form.ElecMenuForm;
import cn.yangml.elec.web.form.ElecSystemDDlForm;


@SuppressWarnings("serial")
public class ElecSystemDDlAction extends BaseAction implements ModelDriven<ElecSystemDDlForm>{
	private IElecSystemDDlService elecSystemDDlService = (IElecSystemDDlService)ServiceProvider.getService(IElecSystemDDlService.SERVICE_NAME);
	private ElecSystemDDlForm elecSystemDDlForm  = new ElecSystemDDlForm();
	public ElecSystemDDlForm getModel() {
		// TODO Auto-generated method stub
		return elecSystemDDlForm;
	}
	/**  
	* @Name: home
	* @Description: 查询数据类型，且去掉重复值
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-01-07 （创建日期）
	* @Parameters: 无
	* @Return: String home 跳转到dictionaryIndex.jsp
	*/
	public String home(){
		List<ElecSystemDDlForm> list = elecSystemDDlService.findKeyWord();
		request.setAttribute("systemList", list);
		return "home";
	}
	
	/**  
	* @Name: edit
	* @Description: 根据选中数据类型，跳转到编辑此数据类型的页面
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-18 （创建日期）
	* @Parameters: 无
	* @Return: String edit 跳转到dictionaryEdit.jsp
	*/
	public String edit(){
		//获取数据类型
		String keyword = elecSystemDDlForm.getKeyword();
		List<ElecSystemDDlForm> list = elecSystemDDlService.findElecSystemDDlListByKeyword(keyword);
		request.setAttribute("systemList", list);
		return "edit";
	}
	/**  
	* @Name: save
	* @Description: 保存数据字典
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-19 （创建日期）
	* @Parameters: 无
	* @Return: String save 重定向到dictionaryIndex.jsp
	*/
	public String save(){
		elecSystemDDlService.saveElecSystemDDl(elecSystemDDlForm);
		return "save";
	}
}
