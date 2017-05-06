package com.allen.web.model;

/**
 * 
* @ClassName: VolunteerXCheck 
* @Description: 用于组装分配志愿者列表 
* @author wenquan
* @date 2017年5月6日 上午11:59:38 
*
 */
public class VolunteerXCheck extends Volunteer {
	boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
