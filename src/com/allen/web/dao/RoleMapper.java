package com.allen.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericDao;
import com.allen.web.model.Role;
import com.allen.web.model.RoleExample;

public interface RoleMapper extends GenericDao<Role, String>{
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);
    
    List<Role> selectByExample(Page<Role> page, RoleExample example);

    Role selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}