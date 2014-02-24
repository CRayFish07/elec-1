package cn.yangml.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.yangml.elec.dao.IElecUserDao;
import cn.yangml.elec.domain.ElecUser;
@Repository(IElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements IElecUserDao{

}
