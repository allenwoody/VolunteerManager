package com.allen.web.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.core.common.Const;
import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericController;
import com.allen.web.enums.EnumBool;
import com.allen.web.model.Permission;
import com.allen.web.model.Role;
import com.allen.web.security.PermissionSign;
import com.allen.web.service.role.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/admin/role")
@Controller
public class RoleController extends GenericController {
	
	@Resource
	
	RoleService roleService;
	/**
	 * 角色列表
	 * @param map
	 * @return
	 */
	@RequestMapping("/listRole")
	@RequiresPermissions(value = PermissionSign.ROLE_QUERY)
	public String listRole(
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "roleSign", required = false) String roleSign,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			@RequestParam(value = "oprationCode", required = false) String oprationCode,
			@RequestParam(value = "keyword", required = false) String keyword,
			ModelMap map){
		if(StringUtils.isNotEmpty(oprationCode)){
//			map.put("msg", EnumOpration.toMap().get(oprationCode));
		}
		
		if (StringUtils.isBlank(pageNo)) {
			pageNo="1";
		}
		Page<Role> page = new Page<>(Integer.parseInt(pageNo), Const.PAGE_SIZE);
		List<Role> roleList = this.roleService.selectList(page);
		map.put("roleList", roleList);
		map.put("page", page);
		return "/role/listRole";
	}
	
	/**
	 * 角色新增页面
	 * @return
	 */
	@RequestMapping("/addRole")
	@RequiresPermissions(value = PermissionSign.ROLE_CREATE)
	public String addRole(){
		
		return "/role/addRole";
	}
	
	/**
	 * 提交新增角色
	 * @param roleName
	 * @return
	 */
	@RequestMapping("/insertRole")
	@RequiresPermissions(value = PermissionSign.ROLE_CREATE)
	public String insertRole(
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "roleSign", required = false) String roleSign,
			ModelMap map){
		Role model = new Role();
		model.setRoleId(UUID.randomUUID().toString());
		model.setRoleName(roleName);
		model.setRoleSign(roleSign);
		model.setIsValid(EnumBool.YES.getCode());
		int i = this.roleService.insert(model);
		if(i==1){
//			map.put("oprationCode", EnumOpration.INSERT_SUCCESS.getCode());
		}
		return "redirect:/admin/role/listRole.html";
	}
	
	/**
	 * 角色修改页面
	 * @param roleId
	 * @param map
	 * @return
	 */
	@RequestMapping("/editRole")
	@RequiresPermissions(value = PermissionSign.ROLE_UPDATE)
	public String editRole(@RequestParam(value = "roleId", required = false) String roleId,
			ModelMap map){
		Role role = this.roleService.selectById(roleId);
		map.put("role", role);
		return "/role/editRole";
	}
	
	/**
	 * 提交角色修改
	 * @param roleId
	 * @param rolename
	 * @return
	 */
	@RequestMapping("/updateRole")
	@RequiresPermissions(value = PermissionSign.ROLE_UPDATE)
	public String updateRole(@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "roleSign", required = false) String roleSign,
			ModelMap map){
		Role model = new Role();
		model.setRoleId(roleId);
		model.setRoleName(roleName);
		model.setRoleSign(roleSign);
		int i = this.roleService.update(model);
		if(i==1){
//			map.put("oprationCode", EnumOpration.UPDATE_SUCCESS.getCode());
		}
		return "redirect:/admin/role/listRole.html";
	}
	
	@RequestMapping("/deleteRole")
	@RequiresPermissions(value = PermissionSign.ROLE_DELETE)
	public String deleteRole(@RequestParam(value = "roleId", required = false) String roleId,
			ModelMap map){
		int i = this.roleService.delete(roleId);
		if(i==1){
//			map.put("oprationCode", EnumOpration.DELETE_SUCCESS.getCode());
		}
		return "redirect:/admin/role/listRole.html";
	}
	
//	/**
//	 * 获取角色（Datagrid）
//	 * @return
//	 */
//	@RequestMapping("/getRoles")
//	@ResponseBody
//	public String getRoles(){
//		Page<Role> page = new Page<>(1,Const.PAGE_SIZE);
//		List<Role> list = this.roleService.selectList(page );
//		ObjectMapper objectMapper = new ObjectMapper();  
//		String result="";
//		try {
//			result = objectMapper.writeValueAsString(list);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	
	/**
	 * 获取未分配给指定角色的功能权限
	 * @param roleId
	 * @param map
	 * @return
	 */
	@RequestMapping("/getAvailablePermissions")
	@ResponseBody
	public String getAvailablePermissions(
			@RequestParam(value = "roleId", required = false) String roleId,
			ModelMap map){
		ObjectMapper objectMapper = new ObjectMapper();  
		String result="";
		List<Permission> list = this.roleService.selectAvailablePermissions(roleId);
		try {
			result = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取已分配给指定角色的功能权限
	 * @param roleId
	 * @param map
	 * @return
	 */
	@RequestMapping("/getExistingPermissions")
	@ResponseBody
	public String getExistingPermissions(
			@RequestParam(value = "roleId", required = false) String roleId,
			ModelMap map){
		ObjectMapper objectMapper = new ObjectMapper();  
		String result="";
		List<Permission> list = this.roleService.selectExistingPermissions(roleId);
		try {
			result = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 保存功能权限分配
	 * @param permisisonids
	 * @param map
	 * @return
	 */
	@RequestMapping("/savePermissionAssignment")
	@RequiresPermissions(value=PermissionSign.ROLE_PERMISSIONASSIGNMENT)
	public @ResponseBody Map<String, String> savePermissionAssignment(
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "permissionIds", required = false) List<String> permissionIds){
		Map<String, String> map = new HashMap<String, String>();
		if (permissionIds.isEmpty()) {
			return map;
		}
		this.roleService.insertRolePermissions(roleId, permissionIds);
		map.put("success", "true");
		return map;
	}
	
}
