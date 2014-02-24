package cn.yangml.elec.util;

import java.lang.reflect.ParameterizedType;


public class GenericSuperClass {
	/**  
	* @Name: save
	* @Description: 泛型转换为对应的对象
	* @Author: 杨明亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-12-26 （创建日期）
	* @Parameters: Serializable id 主键ID
	* @Return: 无
	*/
	@SuppressWarnings("unchecked")
	public static Class getClass(Class tclass) {
		// TODO Auto-generated method stub
		ParameterizedType pt = (ParameterizedType) tclass.getGenericSuperclass();
		Class entity = (Class)pt.getActualTypeArguments()[0];
		return entity;
	}

}
