/***********************************************************************
 * Module:  EnumSex.java
 * Author:  wenquan
 * Purpose: Defines the Class EnumSex
 ***********************************************************************/

package com.allen.web.enums;

import java.util.*;

  /** 性别:1-男;2-女;3-其他; */
  public enum EnumSex {
     /** 1-男 */
     MALE("1","男"),
     /** 1-女 */
     FEMALE("2","女");
//     /** 2-保密 */
//     OTHER("3","其他")
  
     private String code;
     private String msg;
     private boolean display;
     
     EnumSex(String code, String msg) {
       this.code = code;
       this.msg = msg;
       this.display = true;
     }
     
     public String getCode() {
       return code;
     }
     
     public void setCode(String code) {
       this.code = code;
     }
     
     public String getMsg() {
       return msg;
     }
     
     public void setMsg(String msg) {
       this.msg = msg;
     }
     
     public boolean isDisplay() {
       return display;
     }
     
     public void setDisplay(boolean display) {
       this.display = display;
     }
     
     /**
      * 根据枚举的code返回枚举对象
      * @param code  枚举code值
      * @return  枚举对象
      */
     public static EnumSex getEnumByCode(String code) {
       if (code == null) return null;
       for (EnumSex type : values()) {
           if (type.getCode().equals(code.trim()))
               return type;
       }
       return null;
     }
     
     /**
      * 将枚举转换成code-msg形式的集合
      * 可通过<code>EnumXXX.setDisplay(false);</code>的方式将不需要的枚举类型不转换成map
      * @return  转换后的map集合
      */
     public static Map<String, String> toMap() {
       Map<String, String> enumDataMap = new LinkedHashMap<String, String>();
       for (EnumSex type : EnumSex.values()) {
           if (type.isDisplay()) enumDataMap.put(type.getCode(), type.getMsg());
       }
       return enumDataMap;
     }
     
     /**
      * 将枚举转换成code-code-msg形式的集合
      * 可通过<code>EnumXXX.setDisplay(false);</code>的方式将不需要的枚举类型不转换成map
      * @return  转换后的map集合
      */
     public static Map<String, String> toMixMap() {
       Map<String, String> enumDataMap = new LinkedHashMap<String, String>();
       for (EnumSex type : EnumSex.values()) {
           if (type.isDisplay()) enumDataMap.put(type.getCode(), type.getCode() + "-" + type.getMsg());
       }
       return enumDataMap;
     }
  
  }