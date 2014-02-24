package jnuit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.yangml.elec.dao.IElecTextDao;
import cn.yangml.elec.domain.ElecText;
import cn.yangml.elec.util.container.ServiceProvider;

public class TestDao {
	//保存
	@Test
	public void saveElecText(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao =  (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		//实例化PO对象，赋值，保存
		ElecText elText = new ElecText();
		elText.setTextName("test Dao name");
		elText.setTextDate(new Date());
		elText.setTextRemark("test comment");
		elecTextDao.save(elText);
	}
	//修改
	@Test
	public void update(){
		//ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao =  (IElecTextDao)ServiceProvider.getService(IElecTextDao.SERVICE_NAME);
		
		//实例化PO对象，赋值，保存
		ElecText elText = new ElecText();
		elText.setTextID("40285f81431acb3b01431acb3dcb0001");
		elText.setTextName("yangml");
		elText.setTextDate(new Date());
		elText.setTextRemark("old");
		elecTextDao.update(elText);
	}
	//通过ID查询
	@Test
	public void findObjectById(){
		//ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao =  (IElecTextDao)ServiceProvider.getService(IElecTextDao.SERVICE_NAME);
		Serializable id="40285f81431acb3b01431acb3dcb0001";
		ElecText elecText =  (ElecText)elecTextDao.findObjectById(id);
		System.out.println(elecText.getTextName());
	}
	//通过ID删除
	@Test
	public void deleteObjectByIds(){
		//ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao =  (IElecTextDao)ServiceProvider.getService(IElecTextDao.SERVICE_NAME);
		Serializable [] id={"40285f81431acb3b01431acb3dcb0001","40285f81431acdf401431acdf6000001"};
		elecTextDao.deleteObjectByIds(id);
	}
	//通过集合对象删除
	@Test
	public void deleteObjectByCollection(){
		//ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao =  (IElecTextDao)ServiceProvider.getService(IElecTextDao.SERVICE_NAME);
		List<ElecText> list = new ArrayList<ElecText>();
		ElecText elecText = new ElecText();
		elecText.setTextID("40285f81431fccc901431fcccc0d0001");
		ElecText elecText1 = new ElecText();
		elecText1.setTextID("40285f81431fd20101431fd203e20001");
		list.add(elecText);
		list.add(elecText1);
		elecTextDao.deleteObjectByCollection(list);
	}
}
