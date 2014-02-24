package cn.yangml.elec.dao.impl;


import org.springframework.stereotype.Repository;

import cn.yangml.elec.dao.IElecTextDao;
import cn.yangml.elec.domain.ElecText;
@Repository(IElecTextDao.SERVICE_NAME)
public class ElecTextDaoImpl extends CommonDaoImpl<ElecText> implements IElecTextDao {

}
