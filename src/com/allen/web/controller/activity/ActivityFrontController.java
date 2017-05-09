package com.allen.web.controller.activity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.allen.core.generic.GenericController;
import com.allen.core.util.ApplicationUtils;
import com.allen.web.model.Activity;
import com.allen.web.model.Volunteer;
import com.allen.web.model.VolunteerActivity;
import com.allen.web.service.activity.ActivityService;
import com.allen.web.service.volunteer.VolunteerActivityService;
import com.allen.web.service.volunteer.VolunteerService;
/**
 * 
* @ClassName: ActivityFrontController 
* @Description: 志愿活动-前端 controller
* @author wenquan
* @date 2017年5月9日 上午12:33:17 
*
 */
@Controller
public class ActivityFrontController extends GenericController{

	@Resource
	ActivityService activityService;
	@Resource
	VolunteerActivityService volunteerActivityService;
	@Resource
	VolunteerService volunteerService;
	
	/**
	 * 
	* @Title: activityInfo 
	* @Description: 活动详情 
	* @param @param activityId
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/activity/{activityId}")
	public String activityInfo(
			@PathVariable("activityId") String activityId,
			ModelMap map){
		
		Activity activity = this.activityService.selectById(activityId);
		List<VolunteerActivity> vaList = this.volunteerActivityService.selectListByActivityId(activityId);
		List<String> volunteerIds = new ArrayList<>();
		for (VolunteerActivity volunteerActivity : vaList) {
			volunteerIds.add(volunteerActivity.getVolunteerId());
		}
		if (!volunteerIds.isEmpty()) {
			List<Volunteer> volunteerList = this.volunteerService.selecyByIds(volunteerIds);
			for (Volunteer volunteer : volunteerList) {
				volunteer.setMobile(ApplicationUtils.getStarString2(volunteer.getMobile(), 3, 4));
			}
			map.put("volunteerList", volunteerList);
		}
		map.put("activity", activity);
		return "/activity/activityInfo";
	}
	
}
