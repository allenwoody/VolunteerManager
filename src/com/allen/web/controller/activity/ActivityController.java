package com.allen.web.controller.activity;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.allen.core.common.Const;
import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericController;
import com.allen.core.util.ApplicationUtils;
import com.allen.web.model.Activity;
/**
 * 
* @ClassName: ActivityController 
* @Description: 志愿活动Controller 
* @author wenquan
* @date 2017年5月3日 下午3:51:12 
*
 */
import com.allen.web.service.activity.ActivityService;
@Controller
public class ActivityController extends GenericController{

	@Resource
	ActivityService activityService;
	
	Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	/**
	 * 
	* @Title: listActivity 
	* @Description: 志愿活动列表
	* @param @param Activity
	* @param @param pageNo
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/listActivity")
	public String listActivity(
			Activity activity,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap map){
		
		if (StringUtils.isBlank(pageNo)) {
			pageNo="1";
		}
		Page<Activity> page = new Page<>(Integer.parseInt(pageNo), Const.PAGE_SIZE);
		List<Activity> volenteers = this.activityService.selectList(page);
		map.put("activitys", volenteers);
		map.put("page", page);
		return "/activity/listActivity";
	}
	
	/**
	 * 
	* @Title: addActivity 
	* @Description: 志愿活动新增 
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/addActivity")
	public String addActivity(ModelMap map){
		
		return "/addActivity";
	}
	
	/**
	 * 
	* @Title: insertActivity 
	* @Description: 插入新的志愿活动 
	* @param @param Activity
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/insertActivity",method=RequestMethod.POST)
	public String insertActivity(Activity activity, ModelMap map){
		activity.setActivityId(ApplicationUtils.randomUUID());
		this.activityService.insert(activity);
		logger.info("插入新的志愿活动信息：{}",activity.getActivityName());
		return "redirct:/Activity/listActivity.html";
	}
	
	/**
	 * 
	* @Title: editActivity 
	* @Description: 编辑志愿活动信息 
	* @param @param ActivityId
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/editActivity")
	public String editActivity(@RequestParam("ActivityId")String ActivityId, ModelMap map){
		map.put("Activity", this.activityService.selectById(ActivityId));
		return "/editActivity";
	}
	
	/**
	 * 
	* @Title: updateActivity 
	* @Description: 更新志愿活动信息
	* @param @param Activity
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/updateActivity",method=RequestMethod.POST)
	public String updateActivity(Activity activity, ModelMap map){
		this.activityService.update(activity);
		logger.info("更新志愿活动信息：{}",activity.getActivityName());
		return "redirct:/Activity/listActivity.html";
	}
	
	/**
	 * 
	* @Title: deleteActivity 
	* @Description: 删除志愿活动 
	* @param @param activityId
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/deleteActivity")
	public String deleteActivity(@RequestParam(value = "activityId", required = false) String activityId,
			ModelMap map) {
		this.activityService.delete(activityId);
		return "redirect:/activity/listActivity.html";
	}
}
