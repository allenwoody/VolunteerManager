package com.allen.core.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hundsun.jresplus.beans.config.PropertyPlaceholderConfigurer;

/**
 * 
* @ClassName: PropertyService 
* @Description: PropertyService
* @author wenquan
* @date 2016年1月7日 下午12:04:21 
*
 */
//@Service
public class PropertyService {

	@Resource
	private PropertyPlaceholderConfigurer  propertyPlaceholderConfigurer;
	public static Map propsMap=new HashMap();

	public  String getParam(String name){
		if(propsMap.isEmpty()){
			try {
				Properties p = propertyPlaceholderConfigurer.mergeProperties();
				for(Object o:p.keySet()){
					propsMap.put(o.toString(), p.get(o));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return propsMap.get(name)!=null?propsMap.get(name).toString():"";
	}
	
	public  String getParam(String name,String defaultValue){
		String value=this.getParam(name);
		return StringUtils.isNotBlank(value)?value:defaultValue;
	}
}
