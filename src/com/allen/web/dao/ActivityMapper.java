package com.allen.web.dao;

import com.allen.core.generic.GenericDao;
import com.allen.web.model.Activity;
import com.allen.web.model.ActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityMapper extends GenericDao<Activity, String>{
    long countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(String activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(String activityId);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
}