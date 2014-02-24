package cn.yangml.elec.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.yangml.elec.dao.IElecTextDao;
import cn.yangml.elec.domain.ElecText;
import cn.yangml.elec.service.IElecTextService;
@Transactional(readOnly=true)
@Service(IElecTextService.SERVICE_NAME)
public class ElecTextServiceImpl implements IElecTextService {
	
	@Resource(name=IElecTextDao.SERVICE_NAME)
	private IElecTextDao  elecTextDao;
	
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecText(ElecText elecText) {
		// TODO Auto-generated method stub
		elecTextDao.save(elecText);
	}

}
