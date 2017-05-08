package com.allen.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ApplicationUtils : 程序工具类，提供大量的便捷方法
 *
 */
public class ApplicationUtils {

	static Logger logger = LoggerFactory.getLogger(ApplicationUtils.class);
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
    /**
     * 
    * @Title: getYear 
    * @Description: 获取年份 
    * @param @param date
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public static String getYear(String str){
    	Date date;
		try {
			date = DateUtils.parseDate(str, "yyyy-MM-dd");
			return DateFormatUtils.format(date, "yyyy");
		} catch (ParseException e) {
			logger.error("Date Parse Exception:"+e);
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 
    * @Title: getMonth 
    * @Description: 获取月份 
    * @param @param date
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public static String getMonth(String str){
    	Date date;
		try {
			date = DateUtils.parseDate(str, "yyyy-MM-dd");
			return DateFormatUtils.format(date, "MM");
		} catch (ParseException e) {
			logger.error("Date Parse Exception:"+e);
			e.printStackTrace();
			return null;
		}
    }
    /**
     * 
    * @Title: getDay 
    * @Description: 获取日 
    * @param @param date
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public static String getDay(String str){
    	Date date;
		try {
			date = DateUtils.parseDate(str, "yyyy-MM-dd");
			return DateFormatUtils.format(date, "dd");
		} catch (ParseException e) {
			logger.error("Date Parse Exception:"+e);
			e.printStackTrace();
			return null;
		}
    }
    
    /** 
     * 对字符串处理:将指定位置到指定位置的字符以星号代替 
     *  
     * @param content 
     *            传入的字符串 
     * @param begin 
     *            开始位置 
     * @param end 
     *            结束位置 
     * @return 
     */  
    public static String getStarString(String content, int begin, int end) {  
  
        if (begin >= content.length() || begin < 0) {  
            return content;  
        }  
        if (end >= content.length() || end < 0) {  
            return content;  
        }  
        if (begin >= end) {  
            return content;  
        }  
        String starStr = "";  
        for (int i = begin; i < end; i++) {  
            starStr = starStr + "*";  
        }  
        return content.substring(0, begin) + starStr + content.substring(end, content.length());  
  
    }  
      
    /** 
     * 对字符加星号处理：除前面几位和后面几位外，其他的字符以星号代替 
     *  
     * @param content 
     *            传入的字符串 
     * @param frontNum 
     *            保留前面字符的位数 
     * @param endNum 
     *            保留后面字符的位数 
     * @return 带星号的字符串 
     */  
  
    public static String getStarString2(String content, int frontNum, int endNum) {  
  
        if (frontNum >= content.length() || frontNum < 0) {  
            return content;  
        }  
        if (endNum >= content.length() || endNum < 0) {  
            return content;  
        }  
        if (frontNum + endNum >= content.length()) {  
            return content;  
        }  
        String starStr = "";  
        for (int i = 0; i < (content.length() - frontNum - endNum); i++) {  
            starStr = starStr + "*";  
        }  
        return content.substring(0, frontNum) + starStr  
                + content.substring(content.length() - endNum, content.length());  
  
    }  
    
}
