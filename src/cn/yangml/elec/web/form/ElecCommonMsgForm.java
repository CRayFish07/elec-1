package cn.yangml.elec.web.form;


/**
 * VO对象，对应页面表单的属性
 * @author bling
 *
 */
public class ElecCommonMsgForm {
	private String comID;	//主键ID
	private String stationRun;	//站点运行情况
	private String devRun;	//设备运行情况
	private String createDate;	//创建日期
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getStationRun() {
		return stationRun;
	}
	public void setStationRun(String stationRun) {
		this.stationRun = stationRun;
	}
	public String getDevRun() {
		return devRun;
	}
	public void setDevRun(String devRun) {
		this.devRun = devRun;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
