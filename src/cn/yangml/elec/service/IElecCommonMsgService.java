package cn.yangml.elec.service;

import java.util.List;

import cn.yangml.elec.web.form.ElecCommonMsgForm;

public interface IElecCommonMsgService {
	public final static String SERVICE_NAME="cn.yangml.elec.service.impl.ElecCommonMsgServiceImpl";

	List<ElecCommonMsgForm> findElecCommonMsgList();

	void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm);

	List<ElecCommonMsgForm> findElecCommonMsgListByCurrentDate();
}
