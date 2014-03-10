package cn.yangml.elec.service;

import java.util.Hashtable;
import java.util.List;

import cn.yangml.elec.domain.ElecUser;
import cn.yangml.elec.web.form.ElecUserForm;

public interface IElecUserService {
	public final static String SERVICE_NAME = "cn.yangml.elec.service.impl.ElecUserServiceImpl";

	List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm);

	void saveElecUser(ElecUserForm elecUserForm);

	String checkLogonName(String logonName);

	ElecUserForm findElecUser(ElecUserForm elecUserForm);

	void deleteElecUser(ElecUserForm elecUserForm);

	ElecUser findElecUserByLogonName(String name);

	String findElecPopedomByLogonName(String name);

	Hashtable<String, String> findElecRoleByLogonName(String name);
}
