package cn.yangml.elec.service;

import java.util.List;

import cn.yangml.elec.web.form.ElecSystemDDlForm;



public interface IElecSystemDDlService {
	public final static String SERVICE_NAME="cn.yangml.elec.service.impl.ElecSystemDDlServiceImpl";

	List<ElecSystemDDlForm> findKeyWord();

	List<ElecSystemDDlForm> findElecSystemDDlListByKeyword(String keyword);

	void saveElecSystemDDl(ElecSystemDDlForm elecSystemDDlForm);
}
