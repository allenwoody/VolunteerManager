package com.allen.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.allen.core.generic.GenericDao;
import com.allen.web.model.UserRole;
import com.allen.web.model.UserRoleExample;

public interface UserRoleMapper extends GenericDao<UserRole, String>{
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(String urId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(String urId);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}