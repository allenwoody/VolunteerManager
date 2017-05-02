package com.allen.web.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName:EnumValidateErroMessage
 * @Description:校验失败信息
 * @author:wenquan
 * @date:2015年10月13日
 */
public enum EnumValidateErroMessage {
	UNIQUE_ORGNAME("unique_orgname", "机构名称已存在"), 
	UNIQUE_ORGCODE("unique_orgcode", "机构代码已存在"), 
	EXIST_CHILD_ORG("exist_child_org", "存在子机构，不允许删除"), 
	EXIST_ORG_USER("exist_org_user", "存在该所属机构的用户，不允许删除"),
	EXIST_STAFF_USER("exist_staff_user", "存在该所属机构的员工，不允许删除"),
	EXIST_USER_LOGINNAME("exist_user_loginname", "已存在的用户登录名，登录名不能重复"),
	SUCCESS("success", "校验通过"),
	;
	private String code;
	private String msg;
	private boolean display;

	private EnumValidateErroMessage(String code, String msg) {
		this.code = code;
		this.msg = msg;
		this.display = true;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isDisplay() {
		return this.display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	/**
	 * 根据枚举的code返回枚举对象
	 * 
	 * @param code
	 *            枚举code值
	 * @return 枚举对象
	 */
	public static EnumValidateErroMessage getEnumByCode(String code) {
		if (code == null) {
			return null;
		}
		for (EnumValidateErroMessage type : values()) {
			if (type.getCode().equals(code.trim())) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 将枚举转换成code-msg形式的集合 可通过<code>EnumXXX.setDisplay(false);</code>
	 * 的方式将不需要的枚举类型不转换成map
	 * 
	 * @return 转换后的map集合
	 */
	public static Map<String, String> toMap() {
		Map<String, String> enumDataMap = new LinkedHashMap<String, String>();
		for (EnumValidateErroMessage type : values()) {
			if (type.isDisplay()) {
				enumDataMap.put(type.getCode(), type.getMsg());
			}
		}
		return enumDataMap;
	}

	/**
	 * 将枚举转换成code-code-msg形式的集合 可通过<code>EnumXXX.setDisplay(false);</code>
	 * 的方式将不需要的枚举类型不转换成map
	 * 
	 * @return 转换后的map集合
	 */
	public static Map<String, String> toMixMap() {
		Map<String, String> enumDataMap = new LinkedHashMap<String, String>();
		for (EnumValidateErroMessage type : values()) {
			if (type.isDisplay()) {
				enumDataMap.put(type.getCode(), type.getCode() + "-" + type.getMsg());
			}
		}
		return enumDataMap;
	}
}
