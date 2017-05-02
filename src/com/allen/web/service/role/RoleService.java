package com.allen.web.service.role;

import java.util.List;

import com.allen.core.generic.GenericService;
import com.allen.web.model.Permission;
import com.allen.web.model.Role;
import com.allen.web.model.User;

public interface RoleService extends GenericService<Role, String> {

	/**
	 * 获取未分配给指定角色的功能权限
	 * @param roleid
	 * @return
	 */
	List<Permission> selectAvailablePermissions(String roleId);

	/**
	 * 获取已分配给指定角色的功能权限
	 * @param roleid
	 * @return
	 */
	List<Permission> selectExistingPermissions(String roleId);
	
	/**
	 * 插入角色权限
	 * @param roleId
	 * @param permissionIds
	 * @return
	 */
	int insertRolePermissions(String roleId, List<String> permissionIds);

	/**
	 * 按用户查询角色
	 * @param userid
	 * @return
	 */
	List<Role> selectRolesByUserId(String userId);

	/**
	 * 
	* @Title: selectUserOwnRole 
	* @Description: 按照角色查询拥有该角色的用户 
	* @param @param roleId
	* @param @return    设定文件 
	* @return List<User>    返回类型 
	* @throws
	 */
	List<User> selectUserOwnRole(String roleId);
}
