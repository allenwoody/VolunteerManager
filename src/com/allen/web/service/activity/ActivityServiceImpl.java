package com.allen.web.service.activity;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericDao;
import com.allen.core.generic.GenericServiceImpl;
import com.allen.web.dao.ActivityMapper;
import com.allen.web.enums.EnumBool;
import com.allen.web.model.Activity;
import com.allen.web.model.ActivityExample;
import com.allen.web.model.ActivityExample.Criteria;
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
		example.setOrderByClause("activity_date");
		return this.activityMapper.selectByExample(example, page);
	}

	@Override
	public int delete(String id) {
		Activity record = new Activity();
		record.setActivityId(id);
		record.setIsValid(EnumBool.NO.getCode());
		return this.activityMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public List<Activity> selectList() {
		ActivityExample example = new ActivityExample();
		example.createCriteria().andIsValidEqualTo(EnumBool.YES.getCode());
		example.setOrderByClause("activity_date desc");
		return this.activityMapper.selectByExample(example);
	}

	@Override
	public List<Activity> selectListSelective(String keyword) {
		ActivityExample example = new ActivityExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsValidEqualTo(EnumBool.YES.getCode());
		if (StringUtils.isNotBlank(keyword)) {
			criteria.andActivityNameLike("%"+keyword+"%");
			Criteria criteria2 = example.createCriteria();
			criteria2.andIsValidEqualTo(EnumBool.YES.getCode()).andActivityDateLike("%"+keyword+"%");
			example.or(criteria2);
		}
		example.setOrderByClause("activity_date desc");
		return this.activityMapper.selectByExample(example);
	}
	
}
