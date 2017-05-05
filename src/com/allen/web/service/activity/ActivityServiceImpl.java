package com.allen.web.service.activity;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericDao;
import com.allen.core.generic.GenericServiceImpl;
import com.allen.web.dao.ActivityMapper;
import com.allen.web.enums.EnumBool;
import com.allen.web.model.Activity;
import com.allen.web.model.ActivityExample;
/**
 * 
* @ClassName: ActivityServiceImpl 
* @Description: 活动Service 
* @author wenquan
* @date 2017年5月3日 下午3:48:48 
*
 */
@Service
public class ActivityServiceImpl extends GenericServiceImpl<Activity, String> implements ActivityService{

	@Resource
	ActivityMapper activityMapper;
	
	@Override
	public GenericDao<Activity, String> getDao() {
		return this.activityMapper;
	}

	@Override
	public List<Activity> selectList(Page<Activity> page) {
		ActivityExample example = new ActivityExample();
		example.createCriteria().andIsValidEqualTo(EnumBool.YES.getCode());
		return this.activityMapper.selectByExample(example, page);
	}

	@Override
	public int delete(String id) {
		Activity record = new Activity();
		record.setActivityId(id);
		record.setIsValid(EnumBool.NO.getCode());
		return this.activityMapper.updateByPrimaryKey(record);
	}
	
}
