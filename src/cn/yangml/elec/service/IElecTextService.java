package cn.yangml.elec.service;

import cn.yangml.elec.domain.ElecText;

public interface IElecTextService {
	public final static String SERVICE_NAME="cn.yangml.elec.dao";
	public void saveElecText(ElecText elecText);
}
