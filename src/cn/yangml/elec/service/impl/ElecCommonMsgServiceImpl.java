package cn.yangml.elec.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.yangml.elec.dao.IElecCommonMsgDao;
import cn.yangml.elec.domain.ElecCommonMsg;
import cn.yangml.elec.service.IElecCommonMsgService;
import cn.yangml.elec.web.form.ElecCommonMsgForm;
@Transactional(readOnly=true)
@Service(IElecCommonMsgService.SERVICE_NAME)
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {
	
	@Resource(name=IElecCommonMsgDao.SERVICE_NAME)
	private IElecCommonMsgDao  elecCommonMsgDao;
	
	/**  
	* @Name: findElecCommonMsgList
	* @Description: 查询所有的代办事宜列表
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-28 （创建日期）
	* @Parameters: 
	* @Return: List<ElecCommonMsgForm> 代办事宜的结果列表
	*/
	public List<ElecCommonMsgForm> findElecCommonMsgList() {
		// TODO Auto-generated method stub
		String hqlwhere = "";
		Object [] params = null;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put(" o.createDate", "desc");
		List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage(hqlwhere, params, orderby);
		List<ElecCommonMsgForm> formlist = this.elecCommonMsgPOListToVOList(list);
		
		return formlist;
	}
	/**  
	* @Name: elecCommonMsgPOListToVOList
	* @Description: 将代办事宜的结果信息从PO对象转换成VO对象
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-28 （创建日期）
	* @Parameters: List<ElecCommonMsg> list PO对象结果集
	* @Return: List<ElecCommonMsgForm> VO对象结果集
	*/
	private List<ElecCommonMsgForm> elecCommonMsgPOListToVOList(
			List<ElecCommonMsg> list) {
		List<ElecCommonMsgForm> formList = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm elecCommonMsgForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			ElecCommonMsg elecCommonMsg = list.get(i);
			elecCommonMsgForm = new ElecCommonMsgForm();
			elecCommonMsgForm.setComID(elecCommonMsg.getComID());
			elecCommonMsgForm.setDevRun(elecCommonMsg.getDevRun());
			elecCommonMsgForm.setStationRun(elecCommonMsg.getStationRun());
			elecCommonMsgForm.setCreateDate(String.valueOf(elecCommonMsg.getCreateDate()!=null?elecCommonMsg.getCreateDate():""));
			formList.add(elecCommonMsgForm);
		}
		return formList;
	}
	/**  
	* @Name: saveElecCommonMsg
	* @Description: 保存代办事宜信息
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-29 （创建日期）
	* @Parameters: ElecCommonMsgForm elecCommonMsgForm VO对象，页面传递的参数值
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm) {
		// TODO Auto-generated method stub
		//VO对象转成PO
		ElecCommonMsg  elecCommonMsg = this.elecCommonMsgVOToPO(elecCommonMsgForm);
		elecCommonMsgDao.save(elecCommonMsg);
	}
	/**  
	* @Name: elecCommonMsgVOToPO
	* @Description: 页面传递的代办事宜信息从VO对象转换成PO对象
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-29 （创建日期）
	* @Parameters: ElecCommonMsgForm elecCommonMsgForm VO对象，页面传递的参数值
	* @Return: ElecCommonMsg PO对象
	*/
	private ElecCommonMsg elecCommonMsgVOToPO(
			ElecCommonMsgForm elecCommonMsgForm) {
		ElecCommonMsg elecCommonMsg = new ElecCommonMsg();
		if(elecCommonMsgForm!=null){
			elecCommonMsg.setStationRun(elecCommonMsgForm.getStationRun());
			elecCommonMsg.setDevRun(elecCommonMsgForm.getDevRun());
			elecCommonMsg.setCreateDate(new Date());
		}
		return elecCommonMsg;
	}
	/**  
	* @Name: findElecCommonMsgListByCurrentDate
	* @Description: 通过当前日期查询代办事宜列表
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-29 （创建日期）
	* @Parameters: 无
	* @Return: List<ElecCommonMsgForm> 代办事宜列表
	*/
	public List<ElecCommonMsgForm> findElecCommonMsgListByCurrentDate() {
		//获取当前日期YYYY-MM-DD
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		String currentDate = date.toString();
		List<Object[]> list = elecCommonMsgDao.findElecCommonMsgListByCurrentDate(currentDate);
		List<ElecCommonMsgForm> formList = this.elecCommonMsgObjectListToVOList(list);
		return formList;
	}
	/**  
	* @Name: elecCommonMsgObjectListToVOList
	* @Description: 将代办事宜查询的结果由Object[]转换成VO对象
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-23 （创建日期）
	* @Parameters: List<Object[]> list 存放Object[]数组对象
	* @Return: List<ElecCommonMsgForm> VO对象集合
	*/
	private List<ElecCommonMsgForm> elecCommonMsgObjectListToVOList(
			List<Object[]> list) {
		List<ElecCommonMsgForm> formList = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm elecCommonMsgForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			Object[] object = list.get(i);
			elecCommonMsgForm = new ElecCommonMsgForm();
			elecCommonMsgForm.setStationRun(object[0].toString());
			elecCommonMsgForm.setDevRun(object[1].toString());
			formList.add(elecCommonMsgForm);
		}
		return formList;
	}
	


}
