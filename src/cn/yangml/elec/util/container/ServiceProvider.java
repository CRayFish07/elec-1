package cn.yangml.elec.util.container;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("static-access")
public class ServiceProvider {
	public static ServiceProviderCord spc;
	//加载beans.xml文件
	static{
		System.out.println("bbbb");
		spc = new ServiceProviderCord();
		spc.load("beans.xml");
	}
	/**  
	* @Name: getService
	* @Description: 自定义spring容器，加载服务节点
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-24 （创建日期）
	* @Parameters: String serviceName 服务节点名称
	* @Return: Object 服务接口
	*/
	public static Object getService(String serviceName){
		if(StringUtils.isBlank(serviceName)){
			throw new RuntimeException("当前服务名称不存在");
		}
		Object object = null;
		if(spc.ac.containsBean(serviceName)){
			object = spc.ac.getBean(serviceName);
		}
		if(object==null){
			throw new RuntimeException("当前服务名称【"+serviceName+"】下的服务节点不存在");
		}
		return object;
	}
}
