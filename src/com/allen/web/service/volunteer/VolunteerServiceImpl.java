package com.allen.web.service.volunteer;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericDao;
import com.allen.core.generic.GenericServiceImpl;
import com.allen.web.dao.VolunteerMapper;
import com.allen.web.enums.EnumBool;
import com.allen.web.model.Volunteer;
import com.allen.web.model.VolunteerExample;
import com.allen.web.model.VolunteerExample.Criteria;
/**
 * 
* @ClassName: VolunteerServiceImpl 
* @Description: 志愿者Service 
* @author wenquan
* @date 2017年4月30日 下午6:33:47 
*
 */
@Service
public class VolunteerServiceImpl extends GenericServiceImpl<Volunteer, String> implements VolunteerService{

	@Resource
	VolunteerMapper volunteerMapper;
	
	@Override
	public GenericDao<Volunteer, String> getDao() {
		return this.volunteerMapper;
	}

	@Override
	public List<Volunteer> selectList(Page<Volunteer> page) {
		VolunteerExample example = new VolunteerExample();
		example.createCriteria().andIsValidEqualTo(EnumBool.YES.getCode());
		example.setOrderByClause("volunteer_name");
		return this.volunteerMapper.selectByExample(example, page);
	}

	@Override
	public int delete(String id) {
		Volunteer record = new Volunteer();
		record.setVolunteerId(id);
		record .setIsValid(EnumBool.NO.getCode());
		return this.volunteerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Volunteer> selectBySearchingPage(Volunteer volunteer, Page<Volunteer> page) {
		VolunteerExample example = new VolunteerExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsValidEqualTo(EnumBool.YES.getCode());
		if(StringUtils.isNotBlank(volunteer.getVolunteerName())){
			criteria.andVolunteerNameLike("%"+volunteer.getVolunteerName()+"%");
		}
		if (StringUtils.isNotBlank(volunteer.getMobile())) {
			criteria.andMobileLike("%"+volunteer.getMobile()+"%");
		}
		example.setOrderByClause("volunteer_name");
		return this.volunteerMapper.selectByExample(example, page);
	}
	
	@Override
	public List<Volunteer> selectBySearching(Volunteer volunteer) {
		VolunteerExample example = new VolunteerExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsValidEqualTo(EnumBool.YES.getCode());
		if(StringUtils.isNotBlank(volunteer.getVolunteerName())){
			criteria.andVolunteerNameLike("%"+volunteer.getVolunteerName()+"%");
		}
		if (StringUtils.isNotBlank(volunteer.getMobile())) {
			criteria.andMobileLike("%"+volunteer.getMobile()+"%");
		}
		example.setOrderByClause("volunteer_name");
		return this.volunteerMapper.selectByExample(example);
	}

}
