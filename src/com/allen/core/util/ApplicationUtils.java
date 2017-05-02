package com.allen.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * ApplicationUtils : 程序工具类，提供大量的便捷方法
 *
 */
public class ApplicationUtils {

	
    private ApplicationUtils() {
    	
	}

	/**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    /**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }

    /**
	 * 
	* @Title: rename 
	* @Description: 用于上传文件的重命名
	* @param @param fileName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
    public static String rename(String fileName) {  
	      String body="";  
	      String ext="";  
	      Date date = new Date();  
	      int pot=fileName.lastIndexOf(".");  
	      if(pot!=-1){  
	          body= date.getTime() +"";  
	          ext=fileName.substring(pot);  
	      }else{  
	          body=(new Date()).getTime()+"";  
	          ext="";  
	      }  
	      String newName=body+ext;  
	      return newName;  
	  
	    }  
    
    /**
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * 
    * @Title: mapToObject 
    * @Description: Map转换为JavaBean 
    * @param @param map
    * @param @param beanClass
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws ReflectiveOperationException  {    
        if (map == null)  
            return null;    
  
        Object obj = beanClass.newInstance();  
  
        Field[] fields = obj.getClass().getDeclaredFields();   
        for (Field field : fields) {    
            int mod = field.getModifiers();    
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                continue;    
            }    
  
            field.setAccessible(true);    
            field.set(obj, map.get(field.getName()));   
        }   
  
        return obj;    
    }    
  
    /**
     * @throws IllegalAccessException 
     * @throws  (
     * 
    * @Title: objectToMap 
    * @Description: JavaBean转换为Map 
    * @param @param obj
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException{    
        if(obj == null){    
            return null;    
        }   
  
        Map<String, Object> map = new HashMap<>();    
  
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
  
        return map;  
    }   
}
