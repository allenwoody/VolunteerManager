package com.allen.web.service.user;

import java.util.List;

import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericService;
import com.allen.web.model.Role;
import com.allen.web.model.User;

public interface UserService extends GenericService<User, String> {

	/**
	 * 获取用户的未（可）分配角色
	 * @param userId
	 * @return
	 */
	List<Role> selectAvailableRoles(String userId);

	/**
	 * 获取用户的已分配角色
	 * @param userId
	 * @return
	 */
	List<Role> selectExistingRoles(String userId);

	/**
	 * 设置用户角色
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	int insertUserRoles(String userId, List<String> roleIds);

	/**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     * 
     * @param loginName
     * @return
     */
    User selectByLoginName(String loginName);
    
    /**
     * 分页查找用户
     * @param page
     * @param model
     * @param keyword TODO
     * @return
     */
    List<User> selectBySearching(Page<User> page, User model, String keyword);

    /**
     * 根据所属机构查询用户
     * @param orgid
     * @return
     */
//	List<User> selectUserByOrgid(Long orgid);
}
