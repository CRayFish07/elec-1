package cn.yangml.elec.web.action;


import cn.yangml.elec.domain.ElecText;
import cn.yangml.elec.service.IElecTextService;
import cn.yangml.elec.util.StringHelper;
import cn.yangml.elec.util.container.ServiceProvider;
import cn.yangml.elec.web.form.ElecTextForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecTextAction extends BaseAction implements ModelDriven<ElecTextForm>{
	IElecTextService elecTextService = (IElecTextService)ServiceProvider.getService(IElecTextService.SERVICE_NAME);
	private ElecTextForm elecTextForm = new ElecTextForm();
	public ElecTextForm getModel() {
		// TODO Auto-generated method stub
		return elecTextForm;
	}
	public String save(){
//		System.out.println(elecTextForm.getTextName());
//		System.out.println(request.getParameter("textName"));
//		//VO对象转换成PO对象
		//实例化PO对象
		ElecText elecText = new ElecText();
		elecText.setTextName(elecTextForm.getTextName());//测试名称
		elecText.setTextDate(StringHelper.stringConvertDate(elecTextForm.getTextDate()));//测试日期
		elecText.setTextRemark(elecTextForm.getTextRemark());//测试备注
//		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		IElecTextService elecTextService = (IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
		
		elecTextService.saveElecText(elecText);
		return "save";
	}
}
