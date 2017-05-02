package com.allen.web.dao;

import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericDao;
import com.allen.web.model.Volunteer;
import com.allen.web.model.VolunteerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VolunteerMapper extends GenericDao<Volunteer, String>{
    long countByExample(VolunteerExample example);

    int deleteByExample(VolunteerExample example);

    int deleteByPrimaryKey(String volunteerId);

    int insert(Volunteer record);

    int insertSelective(Volunteer record);

    List<Volunteer> selectByExample(VolunteerExample example);
    
    List<Volunteer> selectByExample(VolunteerExample example, Page<Volunteer> page);

    Volunteer selectByPrimaryKey(String volunteerId);

    int updateByExampleSelective(@Param("record") Volunteer record, @Param("example") VolunteerExample example);

    int updateByExample(@Param("record") Volunteer record, @Param("example") VolunteerExample example);

    int updateByPrimaryKeySelective(Volunteer record);

    int updateByPrimaryKey(Volunteer record);
}