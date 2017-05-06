package com.allen.web.controller.user;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.core.common.Const;
import com.allen.core.entity.JSONResult;
import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericController;
import com.allen.core.util.MD5Util;
import com.allen.web.enums.EnumBool;
import com.allen.web.model.Role;
import com.allen.web.model.User;
import com.allen.web.model.UserInSession;
import com.allen.web.security.PermissionSign;
import com.allen.web.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/admin/user")
@Controller
public class UserController extends GenericController {

	@Resource
	UserService userService;

	@RequestMapping("/listUser")
	@RequiresPermissions(value = PermissionSign.USER_QUERY)
	public String listUser(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "loginname", required = false) String loginname,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			@RequestParam(value = "oprationCode", required = false) String oprationCode,
			@RequestParam(value = "keyword", required = false) String keyword,
			ModelMap map) {
		if (StringUtils.isNotEmpty(oprationCode)) {
//			map.put("msg", EnumOpration.toMap().get(oprationCode));
		}
		
		User model = new User();
		model.setUsername(username);
		if (StringUtils.isBlank(pageNo)) {
			pageNo="1";
		}
		Page<User> page = new Page<>(Integer.parseInt(pageNo), Const.PAGE_SIZE);
		List<User> userList = this.userService.selectBySearching(page , model, keyword);
		map.put("userList", userList);
		map.put("page", page);
		map.put("keyword", keyword);
		return "/user/listUser";
	}

	/**	 * 用户新增页面
	 * 
	 * @return
	 */
	@RequiresPermissions(value = PermissionSign.USER_CREATE)
	@RequestMapping("/addUser")
	public String addUser() {

		return "/user/addUser";
	}

	/**
	 * 提交新增用户
	 * 
	 * @param username
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping("/insertUser")
	@RequiresPermissions(value = PermissionSign.USER_CREATE)
	public String insertUser(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "loginname", required = false) String loginname,
//			@RequestParam(value = "password", required = false) String password,
			ModelMap map) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User model = new User();
		model.setUserId(UUID.randomUUID().toString());
		model.setUsername(username);
		model.setLoginname(loginname);
		model.setPassword(MD5Util.getEncryptedPwd(Const.INITIAL_PASSWORD));
		model.setIsValid(EnumBool.YES.getCode());
		//登录名唯一性校验
		User user = userService.selectByLoginName(loginname);
		if (null!=user) {
			//用户名已存在
//			map.put("validateMsg", EnumValidateErroMessage.EXIST_USER_LOGINNAME.getMsg());
			map.put("model", model);
			return "/user/addUser";
		}
		
		int i = this.userService.insert(model);
		if (i == 1) {
//			map.put("oprationCode", EnumOpration.INSERT_SUCCESS.getCode());
		}
		return "redirect:/admin/user/listUser.html";
	}

	/**
	 * 用户修改页面
	 * 
	 * @param userId
	 * @param map
	 * @return
	 */
	@RequestMapping("/editUser")
	@RequiresPermissions(value = PermissionSign.USER_UPDATE)
	public String editUser(@RequestParam(value = "userId", required = false) String userId, ModelMap map) {
		User user = this.userService.selectById(userId);
		map.put("user", user);
		return "/user/editUser";
	}

	/**
	 * 提交用户修改
	 * 
	 * @param userId
	 * @param username
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping("/updateUser")
	@RequiresPermissions(value = PermissionSign.USER_UPDATE)
	public String updateUser(@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "loginname", required = false) String loginname,
			@RequestParam(value = "password", required = false) String password,
			ModelMap map) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User model = new User();
		model.setUserId(userId);
		model.setUsername(username);
		model.setLoginname(loginname);
		model.setPassword(MD5Util.getEncryptedPwd(password));
		int i = this.userService.update(model);
		if (i == 1) {
//			map.put("oprationCode", EnumOpration.UPDATE_SUCCESS.getCode());
		}else {
//			map.put("oprationCode", EnumOpration.FAILED.getCode());
		}
		return "redirect:/admin/user/listUser.html";
	}

	/**
	 * 
	* @Title: modifyPassword 
	* @Description: 修改当前
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/modifyPassword", method=RequestMethod.GET)
	public String modifyPassword(ModelMap map) {
		String userId = this.getLoginUser().getUserId();
		User user = this.userService.selectById(userId);
		map.put("user", user);
		return "/user/modifyPassword";
	}
	
	
	@RequestMapping(value="/modifyPassword", method=RequestMethod.POST)
	public String submitModifyPassword(@RequestParam(value = "userId", required = false) String userId,
//			@RequestParam(value = "username", required = false) String username,
//			@RequestParam(value = "loginname", required = false) String loginname,
			@RequestParam(value = "password", required = false) String password,
			ModelMap map) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User model = new User();
		model.setUserId(userId);
		model.setPassword(MD5Util.getEncryptedPwd(password));
		this.userService.update(model);
		return "redirect:/index.html";
	}
	
	/**
	 * 
	* @Title: validatePassword 
	* @Description: 校验当前密码 
	* @param @param currentPassword
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JSONResult    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/ajax/validatePassword", method = RequestMethod.POST)
	public JSONResult  validatePassword(@RequestParam("currentPassword") String currentPassword) throws Exception {
		JSONResult jsonResult = new JSONResult<>();
		User user = this.userService.selectById(this.getLoginUser().getUserId());
		if (user.getPassword().equals(MD5Util.getEncryptedPwd(currentPassword))) {
			jsonResult.setSuccess(true);
		}else {
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}
	
	@RequestMapping("/deleteUser")
	@RequiresPermissions(value = PermissionSign.USER_DELETE)
	public String deleteUser(@RequestParam(value = "userId", required = false) String userId, ModelMap map) {
//		if (userId.equals(getLoginUser().getUserId())) {
////			map.put("oprationCode", EnumOpration.CANNOT_DELETE_YOURSELF.getCode());
//			return "redirect:/admin/user/listUser.htm";
//		}
		int i = this.userService.delete(userId);
		if (i == 1) {
//			map.put("oprationCode", EnumOpration.DELETE_SUCCESS.getCode());
		}
		return "redirect:/admin/user/listUser.html";
	}

//	/**
//	 * 获取用户（Datagrid）
//	 * 
//	 * @return
//	 */
//	@RequestMapping("/getUsers")
//	@ResponseBody
//	public String getUsers(
//			@RequestParam(value = "username", required = false) String username,
//			@RequestParam(value = "loginname", required = false) String loginname,
//			@RequestParam(value = "pageNumber", required = false) String pageNumber,
//			@RequestParam(value = "pageSize", required = false) int pageSize,
//			ModelMap map) {
//		if (pageSize==0) {
//			pageSize = Const.PAGE_SIZE;
//		}
//		if (StringUtils.isBlank(pageNumber)) {
//			pageNumber="1";
//		}
//		Page<User> page = new Page<>(Integer.parseInt(pageNumber), pageSize);
//		User model = new User();
//		model.setUsername(username);
//		model.setLoginname(loginname);
//		List<User> list = this.userService.selectBySearching(page, model, null);
//		ObjectMapper objectMapper = new ObjectMapper();
//		String result = "";
//		try {
//			result = "{\"total\" : " + page.getTotalCount() + ",\"rows\":" + objectMapper.writeValueAsString(list) + "}";
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

	/**
	 * 获取未分配给指定用户的角色
	 * 
	 * @param userId
	 * @param map
	 * @return
	 */
	@RequestMapping("/getAvailableRoles")
	@ResponseBody
	public String getAvailableRoles(@RequestParam(value = "userId", required = false) String userId, ModelMap map) {
		ObjectMapper objectMapper = new ObjectMapper();
		String result = "";
		List<Role> list = this.userService.selectAvailableRoles(userId);
		try {
			result = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取已分配给指定用户的角色
	 * 
	 * @param userId
	 * @param map
	 * @return
	 */
	@RequestMapping("/getExistingRoles")
	@ResponseBody
	public String getExistingRoles(@RequestParam(value = "userId", required = false) String userId, ModelMap map) {
		ObjectMapper objectMapper = new ObjectMapper();
		String result = "";
		List<Role> list = this.userService.selectExistingRoles(userId);
		try {
			result = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 保存角色分配
	 * 
	 * @param roleids
	 * @param map
	 * @return
	 */
	@RequestMapping("/saveRoleAssignment")
	@RequiresPermissions(value=PermissionSign.USER_ROLEASSIGNMENT)
	public @ResponseBody Map<String, String> saveRoleAssignment(
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "roleIds", required = false) List<String> roleIds) {
		Map<String, String> map = new HashMap<String, String>();
		if(null==roleIds){
			roleIds = ListUtils.EMPTY_LIST;
		}
		this.userService.insertUserRoles(userId, roleIds);
		map.put("success", "true");
		return map;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		try {
			Subject subject = SecurityUtils.getSubject();
			// 已登陆则 跳到首页
			if (subject.isAuthenticated()) {
				/*判断是否初始密码，若是则跳转至修改密码*/
				if(user.getPassword().equals(Const.INITIAL_PASSWORD)){
					return "redirect:/setPassword.html";
				}
				return "redirect:/index.html";
			}
			if (result.hasErrors()) {
				model.addAttribute("error", "参数错误！");
				return "/login";
			}
			// 身份验证
			subject.login(new UsernamePasswordToken(user.getUsername(), MD5Util.getEncryptedPwd(user.getPassword())));
			// 验证成功在Session中保存用户信息
			final User authUserInfo = userService.selectByLoginName(user.getUsername());
			UserInSession uis = new UserInSession();
			uis.setLoginName(authUserInfo.getLoginname());
			uis.setUsername(authUserInfo.getUsername());
			uis.setUserId(authUserInfo.getUserId());
//			request.getSession().setAttribute(Const.UserInSession, uis);
			this.getSession().setAttribute(Const.UserInSession, uis);
			request.getSession().setAttribute("currentUser", authUserInfo.getUsername());
			/*判断是否初始密码，若是则跳转至修改密码*/
			if(user.getPassword().equals(Const.INITIAL_PASSWORD)){
				return "redirect:/setPassword.html";
			}
		} catch (AuthenticationException e) {
			// 身份验证失败
			model.addAttribute("error", "用户名或密码错误 ！");
			return "/login";
		}
		return "redirect:/index.html";
	}
	
	

	/**
	 * 用户登出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		// 登出操作
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "/login";
	}

	@RequestMapping("/isUnique")
	public @ResponseBody String isUnique(@RequestParam(value = "loginname", required = false) String loginname){
		User user = this.userService.selectByLoginName(loginname);
		if (null==user) {
			return "true";
		}
		return "false";
	}
}
