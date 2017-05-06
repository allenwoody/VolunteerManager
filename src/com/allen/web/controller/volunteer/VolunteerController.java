package com.allen.web.controller.volunteer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.core.common.Const;
import com.allen.core.entity.JSONResult;
import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericController;
import com.allen.core.util.ApplicationUtils;
import com.allen.web.enums.EnumBool;
import com.allen.web.enums.EnumSex;
import com.allen.web.model.Volunteer;
import com.allen.web.model.VolunteerActivity;
import com.allen.web.model.VolunteerXCheck;
import com.allen.web.service.volunteer.VolunteerActivityService;
import com.allen.web.service.volunteer.VolunteerService;

/**
 * 
* @ClassName: VolunteerController 
* @Description: 志愿者Controller 
* @author wenquan
* @date 2017年4月30日 下午6:30:36 
*
 */
@RequestMapping("/admin/volunteer")
@Controller
public class VolunteerController extends GenericController{
	
	Logger logger = LoggerFactory.getLogger(VolunteerController.class);

	@Resource
	VolunteerService volunteerService;
	@Resource
	VolunteerActivityService volunteerActivityService;
	
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
		List<Volunteer> volunteerList = this.volunteerService.selectList(page);
		map.put("volunteerList", volunteerList);
		map.put("page", page);
		map.put("sexMap", EnumSex.toMap());
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
		map.put("sexMap", EnumSex.toMap());
		return "/volunteer/addVolunteer";
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
	public String insertVolunteer(@Valid Volunteer volunteer, ModelMap map){
		volunteer.setVolunteerId(ApplicationUtils.randomUUID());
		volunteer.setIsValid(EnumBool.YES.getCode());
		this.volunteerService.insert(volunteer);
		logger.info("插入新的志愿者信息：{}",volunteer.getVolunteerName());
		return "redirect:/admin/volunteer/listVolunteer.html";
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
		map.put("sexMap", EnumSex.toMap());
		return "/volunteer/editVolunteer";
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
		return "redirect:/admin/volunteer/listVolunteer.html";
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
		return "redirect:/admin/volunteer/listVolunteer.html";
	}
	
	
	/**
	 * 
	* @Title: getVolunteers 
	* @Description: ajax获取志愿者信息 
	* @param @param nameSearch
	* @param @param mobileSearch
	* @param @param activityId
	* @param @param pageNo
	* @param @return    设定文件 
	* @return JSONResult<Object>    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/ajax/getVolunteers")
	public JSONResult<Object> getVolunteers(
			@RequestParam(value = "nameSearch", required = false) String nameSearch,
			@RequestParam(value = "mobileSearch", required = false) String mobileSearch,
			@RequestParam(value = "activityId", required = false) String activityId,
			@RequestParam(value = "pageNo", required = false) String pageNo){
		JSONResult<Object> result = new JSONResult<>(); 
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";// All parameters should be treated as final
							// 不过controller的方法并不会被其他类直接调用，所以无所谓了。
		}
		Page<Volunteer> page = new Page<>(Integer.parseInt(pageNo), 1000);//暂时不想改弹出框分页选择checkbox的问题
		Volunteer volunteer = new Volunteer();
		volunteer.setVolunteerName(nameSearch);
		volunteer.setMobile(mobileSearch);
		List<VolunteerActivity> activityVolunteerList = this.volunteerActivityService.selectListByActivityId(activityId);
		Set<String> volunteerIdSet = new HashSet<>();
		for (VolunteerActivity volunteerActivity : activityVolunteerList) {
			volunteerIdSet.add(volunteerActivity.getVolunteerId());
		}
		
		List<Volunteer> volunteerList = this.volunteerService.selectBySearchingPage(volunteer, page);
		List<VolunteerXCheck> checkList = new ArrayList<>();
		for (Volunteer aVolunteer : volunteerList) {
			VolunteerXCheck aCheck = new VolunteerXCheck();
			try {
				BeanUtils.copyProperties(aCheck, aVolunteer);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException:"+e);
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException:"+e);
				e.printStackTrace();
			}
			if (volunteerIdSet.contains(aCheck.getVolunteerId())) {
				aCheck.setChecked(true);
			}else {
				aCheck.setChecked(false);
			}
			checkList.add(aCheck);
		}
		result.setSuccess(true);
		result.setData(new Object[]{checkList,page});
		return result;
	}
	
}
