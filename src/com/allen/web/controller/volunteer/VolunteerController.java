package com.allen.web.controller.volunteer;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.allen.core.common.Const;
import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericController;
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
}
