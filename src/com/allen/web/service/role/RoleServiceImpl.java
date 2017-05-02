package com.allen.web.service.role;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Service;

import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericDao;
import com.allen.core.generic.GenericServiceImpl;
import com.allen.web.dao.PermissionMapper;
import com.allen.web.dao.RoleMapper;
import com.allen.web.dao.RolePermissionMapper;
import com.allen.web.dao.UserMapper;
import com.allen.web.dao.UserRoleMapper;
import com.allen.web.enums.EnumBool;
import com.allen.web.model.Permission;
import com.allen.web.model.PermissionExample;
import com.allen.web.model.Role;
import com.allen.web.model.RoleExample;
import com.allen.web.model.RolePermission;
import com.allen.web.model.RolePermissionExample;
import com.allen.web.model.User;
import com.allen.web.model.UserExample;
import com.allen.web.model.UserRole;
import com.allen.web.model.UserRoleExample;
import com.allen.web.model.PermissionExample.Criteria;
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, String>implements RoleService {

	@Resource
	RoleMapper roleMapper;
	@Resource
	PermissionMapper permissionMapper;
	@Resource
	RolePermissionMapper rolePermissionMapper;
	@Resource
	UserRoleMapper userRoleMapper;
	@Resource
	UserMapper userMapper;
	
	@Override
	public int delete(String id) {
		Role model = new Role();
		model.setRoleId(id);
		model.setIsValid(EnumBool.NO.getCode());
		return this.roleMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public Role selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> selectList(Page<Role> page) {
		RoleExample example = new RoleExample();
		example.createCriteria().andIsValidEqualTo(EnumBool.YES.getCode());
		example.setOrderByClause("role_sign");
		return this.roleMapper.selectByExample(page, example);
	}

	@Override
	public GenericDao<Role, String> getDao() {
		return this.roleMapper;
	}

	@Override
	public List<Permission> selectAvailablePermissions(String roleId){
		RolePermissionExample rolePermissionExample = new RolePermissionExample();
		rolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
		List<RolePermission> rolePermissionList = this.rolePermissionMapper.selectByExample(rolePermissionExample);
		
		PermissionExample tPermissionExample = new PermissionExample();
		Criteria criteria = tPermissionExample.createCriteria();
		criteria.andIsValidEqualTo(EnumBool.YES.getCode());
		if (rolePermissionList.isEmpty()) {
			//用户未分配角色
			return this.permissionMapper.selectByExample(tPermissionExample);
		}
		
		//用户已分配角色
		List<String> permissionidList = new ArrayList<>();
		for (RolePermission tRolePermission : rolePermissionList) {
			permissionidList.add(tRolePermission.getPermissionId());
		}
		
		criteria.andPermissionIdNotIn(permissionidList);
		return this.permissionMapper.selectByExample(tPermissionExample);
	}

	@Override
	public List<Permission> selectExistingPermissions(String roleId){
		RolePermissionExample tRolePermissionExample = new RolePermissionExample();
		tRolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
		List<RolePermission> rolePermissionList = this.rolePermissionMapper.selectByExample(tRolePermissionExample);
		
		PermissionExample tPermissionExample = new PermissionExample();
		Criteria criteria = tPermissionExample.createCriteria();
		criteria.andIsValidEqualTo(EnumBool.YES.getCode());
		if (rolePermissionList.isEmpty()) {
			//用户未分配角色,返回空列表
			return new ArrayList<Permission>();
		}
		
		//用户已分配角色
		List<String> permissionidList = new ArrayList<>();
		for (RolePermission tRolePermission : rolePermissionList) {
			permissionidList.add(tRolePermission.getPermissionId());
		}
		
		criteria.andPermissionIdIn(permissionidList);
		return this.permissionMapper.selectByExample(tPermissionExample);
	}

	@Override
	public int insertRolePermissions(String roleId, List<String> permissionIds){
		//删除指定用户的全部角色
		RolePermissionExample deleteExample = new RolePermissionExample();
		deleteExample.createCriteria().andRoleIdEqualTo(roleId);
		this.rolePermissionMapper.deleteByExample(deleteExample);
		
		//新增数据
		RolePermission prototypeModel = new RolePermission();
		int count = 0;
		int size = permissionIds.size();
		for (int i = 0; i < size; i++) {
			RolePermission model;
			try {
				model = (RolePermission) BeanUtils.cloneBean(prototypeModel);
			model.setRpId(UUID.randomUUID().toString());
			model.setRoleId(roleId);
			model.setPermissionId(permissionIds.get(i));
			count = count + rolePermissionMapper.insert(model);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public List<Role> selectRolesByUserId(String userId) {
		UserRoleExample urExample = new UserRoleExample();
		urExample.createCriteria().andUserIdEqualTo(userId);
		List<UserRole> urList = userRoleMapper.selectByExample(urExample);
		if (urList.isEmpty()) {
			return ListUtils.EMPTY_LIST;
		}
		List<String> roleidList = new ArrayList<>();
		for (UserRole tUserRole : urList) {
			roleidList.add(tUserRole.getRoleId());
		}
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andIsValidEqualTo(EnumBool.YES.getCode()).andRoleIdIn(roleidList);
		
		return this.roleMapper.selectByExample(roleExample);
	}

	@Override
	public List<User> selectUserOwnRole(String roleId) {
		UserRoleExample ueExample = new UserRoleExample();
		ueExample.createCriteria().andRoleIdEqualTo(roleId);
		List<UserRole> ueList = this.userRoleMapper.selectByExample(ueExample);
		List<String> userIdList = new ArrayList<>();
		if (ueList.isEmpty()) {
			return ListUtils.EMPTY_LIST;
		}
		for (UserRole userRole : ueList) {
			userIdList.add(userRole.getUserId());
		}
		UserExample ue = new UserExample();
		ue.createCriteria().andUserIdIn(userIdList);
		return this.userMapper.selectByExample(ue);
	}

}
