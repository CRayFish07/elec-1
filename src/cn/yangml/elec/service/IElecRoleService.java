package cn.yangml.elec.service;

import java.util.List;

import cn.yangml.elec.util.XmlObject;
import cn.yangml.elec.web.action.ElecRoleForm;
import cn.yangml.elec.web.form.ElecUserForm;

public interface IElecRoleService {
	public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecRoleServiceImpl";
	List<XmlObject> readXml();
	List<XmlObject> readEditXml(String roleID);
	List<ElecUserForm> findElecUserListByRoleID(String roleID);

	void saveRole(ElecRoleForm elecRoleForm);
}
