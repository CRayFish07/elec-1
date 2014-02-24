package jnuit;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.yangml.elec.domain.ElecText;

public class TestHibernate {
	@Test
	public void testElecText(){
		Configuration config = new Configuration();
		config.configure();
		//创建sessionFactory对象
		SessionFactory sf = config.buildSessionFactory();
		//打开session，操作数据库
		Session session = sf.openSession();
		//开启事务
		Transaction tr = session.beginTransaction();
		//实例化ElecText对象，添加数据，执行保存操作
		ElecText elecText = new ElecText();
		elecText.setTextName("测试中Hibernate名称");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试Hibernate备注");
		//保存
		session.save(elecText);
		//提交事务
		tr.commit();
		session.close();
	}
}
