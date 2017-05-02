package com.allen.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.allen.core.generic.GenericDao;
import com.allen.web.model.VolunteerActivity;
import com.allen.web.model.VolunteerActivityExample;

public interface VolunteerActivityMapper extends GenericDao<VolunteerActivity, String>{
    long countByExample(VolunteerActivityExample example);

    int deleteByExample(VolunteerActivityExample example);

    int deleteByPrimaryKey(String vaId);

    int insert(VolunteerActivity record);

    int insertSelective(VolunteerActivity record);

    List<VolunteerActivity> selectByExample(VolunteerActivityExample example);

    VolunteerActivity selectByPrimaryKey(String vaId);

    int updateByExampleSelective(@Param("record") VolunteerActivity record, @Param("example") VolunteerActivityExample example);

    int updateByExample(@Param("record") VolunteerActivity record, @Param("example") VolunteerActivityExample example);

    int updateByPrimaryKeySelective(VolunteerActivity record);

    int updateByPrimaryKey(VolunteerActivity record);
}