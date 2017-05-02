package com.allen.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.allen.core.generic.GenericDao;
import com.allen.web.model.RolePermission;
import com.allen.web.model.RolePermissionExample;

public interface RolePermissionMapper extends GenericDao<RolePermission, String>{
    int countByExample(RolePermissionExample example);

    int deleteByExample(RolePermissionExample example);

    int deleteByPrimaryKey(String rpId);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExample(RolePermissionExample example);

    RolePermission selectByPrimaryKey(String rpId);

    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}