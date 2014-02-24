package cn.yangml.elec.web.form;




/**
 * VO值对象，对应页面表单的属性值
 *
 */
@SuppressWarnings("serial")
public class ElecUserForm implements java.io.Serializable {
	
	private String userID;        //主键ID
	private String jctID;         //所属单位code
	private String userName;      //用户姓名
	private String logonName;     //登录名
	private String logonPwd;      //密码
	private String sexID;         //性别
	private String birthday;      //出生日期
	private String address;       //联系地址
	private String contactTel;    //联系电话 
	private String email;         //电子邮箱
	private String mobile;        //手机
	private String isDuty;        //是否在职
	private String onDutyDate;    //入职时间
	private String  offDutyDate;  //离职时间
	private String remark;        //备注
	
	/**
	 * 使用viewflag字段
	 * 判断当前用户跳转的是编辑页面还是明细页面
	 *    * 如果viewflag==null：说明当前操作的是编辑页面
	 *    * 如果viewflag==1:说明当前操作的是明细页面
	 */
	private String viewflag;
	
	public String getViewflag() {
		return viewflag;
	}
	public void setViewflag(String viewflag) {
		this.viewflag = viewflag;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getJctID() {
		return jctID;
	}
	public void setJctID(String jctID) {
		this.jctID = jctID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogonName() {
		return logonName;
	}
	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}
	public String getLogonPwd() {
		return logonPwd;
	}
	public void setLogonPwd(String logonPwd) {
		this.logonPwd = logonPwd;
	}
	public String getSexID() {
		return sexID;
	}
	public void setSexID(String sexID) {
		this.sexID = sexID;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIsDuty() {
		return isDuty;
	}
	public void setIsDuty(String isDuty) {
		this.isDuty = isDuty;
	}
	public String getOnDutyDate() {
		return onDutyDate;
	}
	public void setOnDutyDate(String onDutyDate) {
		this.onDutyDate = onDutyDate;
	}
	public String getOffDutyDate() {
		return offDutyDate;
	}
	public void setOffDutyDate(String offDutyDate) {
		this.offDutyDate = offDutyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
