package jnuit;

import java.util.Date;

import org.junit.Test;

import cn.yangml.elec.domain.ElecText;
import cn.yangml.elec.service.IElecTextService;
import cn.yangml.elec.util.container.ServiceProvider;

public class TestService {
	@Test
	public void saveElecText(){
//		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		IElecTextService elecTextService =  (IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
//		//实例化PO对象，赋值，保存
//		ElecText elText = new ElecText();
//		elText.setTextName("test Service name1");
//		elText.setTextDate(new Date());
//		elText.setTextRemark("test Service comment1");
//		elecTextService.saveElecText(elText);
		IElecTextService elecTextService = (IElecTextService)ServiceProvider.getService(IElecTextService.SERVICE_NAME);
		//实例化PO对象，赋值，保存
		ElecText elText = new ElecText();
		elText.setTextName("test Service name1123");
		elText.setTextDate(new Date());
		elText.setTextRemark("test Service comment1123");
		elecTextService.saveElecText(elText);
	}
}
