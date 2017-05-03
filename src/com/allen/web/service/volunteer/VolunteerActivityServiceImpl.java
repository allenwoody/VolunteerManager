package com.allen.web.service.volunteer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allen.core.generic.GenericDao;
import com.allen.core.generic.GenericServiceImpl;
import com.allen.web.dao.VolunteerActivityMapper;
import com.allen.web.model.VolunteerActivity;

/**
 * 
* @ClassName: VolunteerActivityServiceImpl 
* @Description: 志愿者-活动Service 
* @author wenquan
* @date 2017年5月3日 下午4:00:57 
*
 */
@Service
public class VolunteerActivityServiceImpl extends GenericServiceImpl<VolunteerActivity, String> implements VolunteerActivityService{

	@Resource
	VolunteerActivityMapper volunteerActivityMapper;
	
	@Override
	public GenericDao<VolunteerActivity, String> getDao() {
		return this.volunteerActivityMapper;
	}

}
