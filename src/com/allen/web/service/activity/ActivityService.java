package com.allen.web.service.activity;

import java.util.List;

import com.allen.core.generic.GenericService;
import com.allen.web.model.Activity;

/**
 * 
* @ClassName: ActivityService 
* @Description: 志愿活动Service接口 
* @author wenquan
* @date 2017年5月3日 下午3:45:03 
*
 */
public interface ActivityService extends GenericService<Activity, String>{

	/**
	 * 
	* @Title: selectListSelective 
	* @Description: 按关键词查询活动 
	* @param @param keyword
	* @param @return    设定文件 
	* @return List<Activity>    返回类型 
	* @throws
	 */
	List<Activity> selectListSelective(String keyword);
}
