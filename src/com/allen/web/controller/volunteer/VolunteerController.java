package com.allen.web.controller.volunteer;

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
import com.allen.web.model.Volunteer;
import com.allen.web.service.volunteer.VolunteerService;

/**
 * 
* @ClassName: VolunteerController 
* @Description: 志愿者Controller 
* @author wenquan
* @date 2017年4月30日 下午6:30:36 
*
 */
@RequestMapping("/volunteer")
@Controller
public class VolunteerController extends GenericController{
	
	Logger logger = LoggerFactory.getLogger(VolunteerController.class);

	@Resource
	VolunteerService volunteerService;
	
	/**
	 * 
	* @Title: listVolunteer 
	* @Description: 志愿者列表
	* @param @param volunteer
	* @param @param pageNo
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/listVolunteer")
	public String listVolunteer(
			Volunteer volunteer,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap map){
		
		if (StringUtils.isBlank(pageNo)) {
			pageNo="1";
		}
		Page<Volunteer> page = new Page<>(Integer.parseInt(pageNo), Const.PAGE_SIZE);
		List<Volunteer> volenteers = this.volunteerService.selectList(page);
		map.put("volunteers", volenteers);
		map.put("page", page);
		return "/volunteer/listVolunteer";
	}
	
	/**
	 * 
	* @Title: addVolunteer 
	* @Description: 志愿者新增 
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/addVolunteer")
	public String addVolunteer(ModelMap map){
		
		return "/addVolunteer";
	}
	
	/**
	 * 
	* @Title: insertVolunteer 
	* @Description: 插入新的志愿者 
	* @param @param volunteer
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/insertVolunteer",method=RequestMethod.POST)
	public String insertVolunteer(Volunteer volunteer, ModelMap map){
		volunteer.setVolunteerId(ApplicationUtils.randomUUID());
		this.volunteerService.insert(volunteer);
		logger.info("插入新的志愿者信息：{}",volunteer.getVolunteerName());
		return "redirct:/volunteer/listVolunteer.html";
	}
	
	/**
	 * 
	* @Title: editVolunteer 
	* @Description: 编辑志愿者信息 
	* @param @param volunteerId
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/editVolunteer")
	public String editVolunteer(@RequestParam("volunteerId")String volunteerId, ModelMap map){
		map.put("volunteer", this.volunteerService.selectById(volunteerId));
		return "/editVolunteer";
	}
	
	/**
	 * 
	* @Title: updateVolunteer 
	* @Description: 更新志愿者信息
	* @param @param volunteer
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/updateVolunteer",method=RequestMethod.POST)
	public String updateVolunteer(Volunteer volunteer, ModelMap map){
		this.volunteerService.update(volunteer);
		logger.info("更新志愿者信息：{}",volunteer.getVolunteerName());
		return "redirct:/volunteer/listVolunteer.html";
	}
	
	/**
	 * 
	* @Title: deleteVolunteer 
	* @Description: 删除志愿者 
	* @param @param volunteerId
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/deleteVolunteer")
	public String deleteVolunteer(@RequestParam(value = "volunteerId", required = false) String volunteerId,
			ModelMap map) {
		this.volunteerService.delete(volunteerId);
		return "redirect:/volunteer/listVolunteer.html";
	}
}
