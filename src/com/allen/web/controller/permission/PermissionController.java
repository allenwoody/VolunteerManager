package com.allen.web.controller.permission;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;

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
import com.allen.web.security.PermissionSign;
import com.allen.web.service.permission.PermissionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @ClassName:PermissionController
 * @Description:权限控制器
 * @author:wenquan
 * @date:2015年8月31日
 */
@Controller
@RequestMapping("/admin/permission")
public class PermissionController extends GenericController {
	@Resource
	PermissionService permissionService;
	
	/**
	 * 
	* @Title: lisPermission 
	* @Description: 全县信息查询 
	* @param @param permissionName
	* @param @param permissionSign
	* @param @param pageNo
	* @param @param oprationCode
	* @param @param keyword
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/listPermission")
	@RequiresPermissions(value = PermissionSign.PERMISSION_QUERY)
	public String lisPermission(
			@RequestParam(value = "permissionName", required = false) String permissionName,
			@RequestParam(value = "permissionSign", required = false) String permissionSign,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			@RequestParam(value = "oprationCode", required = false) String oprationCode,
			@RequestParam(value = "keyword", required = false) String keyword,
			ModelMap map){
		if (StringUtils.isBlank(pageNo)) {
			pageNo="1";
		}
		Permission model = new Permission();
		Page<Permission> page = new Page<>(Integer.parseInt(pageNo), Const.PAGE_SIZE);
		List<Permission> permissionList = this.permissionService.selectBySearchingPage(page, model );
		map.put("permissionList", permissionList);
		map.put("page", page);
		return "/permission/listPermission";
	}
	/**
	 * 新增权限
	 * @param map
	 * @return
	 */
	@RequestMapping("/addPermission")
	@RequiresPermissions(value = PermissionSign.PERMISSION_CREATE)
	public String addPermission(ModelMap map){
		List<Permission> menuList = this.permissionService.selectFirstLevelMenus();
		map.put("menuList", menuList);
		return "/permission/addPermission";
	}
	/**
	 * 提交新增
	 * @param permissionName
	 * @param url
	 * @param parentid
	 * @param description
	 * @param sortno
	 * @param map
	 * @return
	 */
	@RequestMapping("/insertPermission")
	@RequiresPermissions(value = PermissionSign.PERMISSION_CREATE)
	public String inserPermission(
			@RequestParam(value = "permissionName", required = false) String permissionName,
			@RequestParam(value = "permissionSign", required = false) String permissionSign,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "sortNo", required = false) Long sortNo,
			ModelMap map){
		Permission model = new Permission();
		model.setPermissionId(UUID.randomUUID().toString());
		model.setPermissionName(permissionName);
		model.setPermissionSign(permissionSign);
		model.setUrl(url);
		model.setIsValid(EnumBool.YES.getCode());
		model.setDescription(description);
		model.setSortNo(sortNo);
		this.permissionService.insert(model);
		return "redirect:/admin/permission/listPermission.html";
	}
	/**
	 * 修改权限
	 * @param permissionId
	 * @param map
	 * @return
	 */
	@RequestMapping("/editPermission")
	@RequiresPermissions(value = PermissionSign.PERMISSION_UPDATE)
	public String ediPermission(
			@RequestParam(value = "permissionId", required = false) String permissionId,
			ModelMap map){
		Permission permission = this.permissionService.selectById(permissionId);
		map.put("permission", permission);
		List<Permission> permissionList = this.permissionService.selectFirstLevelMenus();
		map.put("permissionList", permissionList);
		return "/permission/editPermission";
	}
	/**
	 * 更新权限
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping("/updatePermission")
	@RequiresPermissions(value = PermissionSign.PERMISSION_UPDATE)
	public String updatePermission(
			@Valid Permission model,
			ModelMap map){
		this.permissionService.update(model);
		return "redirect:/admin/permission/listPermission.html";
	}
	
	/**
	 * 
	* @Title: deletePermission 
	* @Description: 删除权限信息 
	* @param @param permissionId
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/deletePermission")
	@RequiresPermissions(value = PermissionSign.PERMISSION_DELETE)
	public String deletePermission(
			@RequestParam(value = "permissionId", required = false) String permissionId,
			ModelMap map){
		this.permissionService.delete(permissionId);
		return "redirect:/admin/permission/listPermission.html";
	}
	
//	/**
//	 * 获取权限（Datagrid）
//	 * @return
//	 */
//	@RequestMapping("/getPermissions")
//	@ResponseBody
//	public String gePermissions(
//			@RequestParam(value = "permissionName", required = false) String permissionName,
//			@RequestParam(value = "pageNumber", required = false) String pageNumber,
//			@RequestParam(value = "pageSize", required = false) int pageSize,
//			ModelMap map){
//		if (pageSize==0) {
//			pageSize = Const.PAGE_SIZE;
//		}
//		if (StringUtils.isBlank(pageNumber)) {
//			pageNumber="1";
//		}
//		Page<Permission> page = new Page<Permission>(Integer.parseInt(pageNumber), pageSize);
//		Permission model = new Permission();
//		model.setPermissionName(permissionName);
//		List<Permission> list = this.permissionService.selectBySearchingPage(page, model);
//		ObjectMapper objectMapper = new ObjectMapper();  
//		String result="";
//		try {
//			result = "{\"total\" : " + page.getTotalCount() + ",\"rows\":" + objectMapper.writeValueAsString(list) + "}";
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	/**
	 * 
	* @Title: gePermissionById 
	* @Description: 异步获取权限信息 
	* @param @param permissionId
	* @param @return
	* @param @throws JsonProcessingException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/getPermissionById")
	public String gePermissionById(@RequestParam(value = "permissionId", required = false) String permissionId) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		Permission vo = this.permissionService.selectById(permissionId);
		return objectMapper.writeValueAsString(vo);
	}
}
