package com.allen.web.service.volunteer;

import java.util.List;

import com.allen.core.generic.GenericService;
import com.allen.web.model.VolunteerActivity;
/**
 * 
* @ClassName: VolunteerActivityService 
* @Description: 志愿者-活动Service接口 
* @author wenquan
* @date 2017年5月3日 下午3:58:36 
*
 */
public interface VolunteerActivityService extends GenericService<VolunteerActivity, String>{
	
	int insertActivityVolenteers(String activityId, String[] volunteerIds);

	/**
	 * 
	* @Title: selectListByActivityId 
	* @Description: 按活动ID查询
	* @param @param activityId
	* @param @return    设定文件 
	* @return List<VolunteerActivity>    返回类型 
	* @throws
	 */
	List<VolunteerActivity> selectListByActivityId(String activityId);

}
