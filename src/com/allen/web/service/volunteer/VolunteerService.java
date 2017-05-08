package com.allen.web.service.volunteer;

import java.util.List;

import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericService;
import com.allen.web.model.Volunteer;

/**
 * 
* @ClassName: VolunteerService 
* @Description: 志愿者Service接口 
* @author wenquan
* @date 2017年4月30日 下午6:32:08 
*
 */
public interface VolunteerService extends GenericService<Volunteer, String>{

	/**
	 * 
	* @Title: selectBySearchingPage 
	* @Description: 按条件分页查询志愿者信息 
	* @param @param volunteer
	* @param @param page
	* @param @return    设定文件 
	* @return List<Volunteer>    返回类型 
	* @throws
	 */
	List<Volunteer> selectBySearchingPage(Volunteer volunteer, Page<Volunteer> page);
	/**
	 * 
	* @Title: selectBySearchingPage 
	* @Description: 按条件查询志愿者信息 
	* @param @param volunteer
	* @param @return    设定文件 
	* @return List<Volunteer>    返回类型 
	* @throws
	 */
	List<Volunteer> selectBySearching(Volunteer volunteer);
	
	/**
	 * 
	* @Title: selecyByIds 
	* @Description: 按id数组查询
	* @param @param volunteerIds
	* @param @return    设定文件 
	* @return List<Volunteer>    返回类型 
	* @throws
	 */
	List<Volunteer> selecyByIds(List<String> volunteerIds);
}
