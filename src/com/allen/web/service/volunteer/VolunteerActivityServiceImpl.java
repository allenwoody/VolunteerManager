package com.allen.web.service.volunteer;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.allen.core.generic.GenericDao;
import com.allen.core.generic.GenericServiceImpl;
import com.allen.core.util.ApplicationUtils;
import com.allen.web.dao.VolunteerActivityMapper;
import com.allen.web.model.VolunteerActivity;
import com.allen.web.model.VolunteerActivityExample;

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

	@Override
	public int insertActivityVolenteers(@RequestParam("activityId") String activityId, @RequestParam("volunteerIds") String[] volunteerIds) {
		int count = 0;
		VolunteerActivityExample example = new VolunteerActivityExample();
		example.createCriteria().andActivityIdEqualTo(activityId);
		this.volunteerActivityMapper.deleteByExample(example);
		if(volunteerIds==null){
			return 0;
		}
		for (int i = 0; i < volunteerIds.length; i++) {
			VolunteerActivity volunteerActivity = new VolunteerActivity();
			volunteerActivity.setActivityId(activityId);
			volunteerActivity.setVolunteerId(volunteerIds[i]);
			volunteerActivity.setVaId(ApplicationUtils.randomUUID());;
			count = count +this.insert(volunteerActivity);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VolunteerActivity> selectListByActivityId(String activityId) {
		if(StringUtils.isBlank(activityId)){
			return ListUtils.EMPTY_LIST;
		}
		VolunteerActivityExample example = new VolunteerActivityExample();
		example.createCriteria().andActivityIdEqualTo(activityId);
		return this.volunteerActivityMapper.selectByExample(example);
	}
	

}
