package com.allen.web.service.user;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.allen.core.feature.orm.mybatis.Page;
import com.allen.core.generic.GenericDao;
import com.allen.core.generic.GenericServiceImpl;
import com.allen.web.dao.RoleMapper;
import com.allen.web.dao.UserMapper;
import com.allen.web.dao.UserRoleMapper;
import com.allen.web.enums.EnumBool;
import com.allen.web.model.Role;
import com.allen.web.model.RoleExample;
import com.allen.web.model.User;
import com.allen.web.model.UserExample;
import com.allen.web.model.UserRole;
import com.allen.web.model.UserRoleExample;
import com.allen.web.model.RoleExample.Criteria;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, String> implements UserService {

	@Resource
	UserMapper userMapper;
	@Resource
	RoleMapper roleMapper;
	@Resource
	UserRoleMapper userRoleMapper;

	@Override
	public int delete(String id) {
		User model = new User();
		model.setUserId(id);
		model.setIsValid(EnumBool.NO.getCode());
		return this.userMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public List<User> selectList(Page<User> page) {
		UserExample example = new UserExample();
		example.createCriteria().andIsValidEqualTo(EnumBool.YES.getCode());
		return this.userMapper.selectByExample(example);
	}

	@Override
	public GenericDao<User, String> getDao() {
		return this.userMapper;
	}
	
	@Override
	public List<Role> selectAvailableRoles(String userid){
		UserRoleExample tUserRoleExample = new UserRoleExample();
		tUserRoleExample.createCriteria().andUserIdEqualTo(userid);
		List<UserRole> userRoleList = this.userRoleMapper.selectByExample(tUserRoleExample);
		
		RoleExample tRoleExample = new RoleExample();
		Criteria criteria = tRoleExample.createCriteria();
		criteria.andIsValidEqualTo(EnumBool.YES.getCode());
		if (userRoleList.isEmpty()) {
			//用户未分配角色
			return this.roleMapper.selectByExample(tRoleExample);
		}
		
		//用户已分配角色
		List<String> roleidList = new ArrayList<>();
		for (UserRole tUserRole : userRoleList) {
			roleidList.add(tUserRole.getRoleId());
		}
		
		criteria.andRoleIdNotIn(roleidList);
		return this.roleMapper.selectByExample(tRoleExample);
	}

	@Override
	public List<Role> selectExistingRoles(String userid){
		UserRoleExample tUserRoleExample = new UserRoleExample();
		tUserRoleExample.createCriteria().andUserIdEqualTo(userid);
		List<UserRole> userRoleList = this.userRoleMapper.selectByExample(tUserRoleExample);
		
		RoleExample tRoleExample = new RoleExample();
		Criteria criteria = tRoleExample.createCriteria();
		criteria.andIsValidEqualTo(EnumBool.YES.getCode());
		if (userRoleList.isEmpty()) {
			//用户未分配角色,返回空列表
			return new ArrayList<Role>();
		}
		
		//用户已分配角色
		List<String> roleIdList = new ArrayList<>();
		for (UserRole tUserRole : userRoleList) {
			roleIdList.add(tUserRole.getRoleId());
		}
		
		criteria.andRoleIdIn(roleIdList);
		return this.roleMapper.selectByExample(tRoleExample);
	}

	@Override
	public int insertUserRoles(String userid, List<String> roleids){
		//删除指定用户的全部角色
		UserRoleExample deleteExample = new UserRoleExample();
		int count = 0;
		deleteExample.createCriteria().andUserIdEqualTo(userid);
		count = count + this.userRoleMapper.deleteByExample(deleteExample);
		
		//新增数据
		UserRole prototypeModel = new UserRole();
		int size = roleids.size();
		for (int i = 0; i < size; i++) {
			UserRole model;
			try {
				model = (UserRole) BeanUtils.cloneBean(prototypeModel);
			model.setUrId(UUID.randomUUID().toString());
			model.setUserId(userid);
			model.setRoleId(roleids.get(i));
			count = count + userRoleMapper.insert(model);
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
    public User authentication(User user) {
        User tUser = this.selectByLoginName(user.getLoginname());
		if(user.getPassword().equals(tUser.getPassword())){
			return tUser;
		}
        return null;
    }

	 @Override
	    public User selectByLoginName(String loginName) {
	        UserExample example = new UserExample();
	        example.createCriteria().andLoginnameEqualTo(loginName).andIsValidEqualTo(EnumBool.YES.getCode());
	        List<User> list;
			try {
				list = userMapper.selectByExample(example);
				if (list.size()>0) {
					return list.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return null;
	    }

	@Override
	public List<User> selectBySearching(Page<User> page, User model, String keyword) {
		UserExample example = new UserExample();
		 com.allen.web.model.UserExample.Criteria criteria = example.createCriteria();
		criteria.andIsValidEqualTo(EnumBool.YES.getCode());
//		if (StringUtils.isNotBlank(model.getUserName())) {
//			criteria.andUserNameLike("%"+model.getUserName()+"%");
//		}
//		if (StringUtils.isNotBlank(model.getLoginName())) {
//			criteria.andLoginNameLike("%"+model.getLoginName()+"%");
//		}
		if (StringUtils.isNotBlank(keyword)) {
			criteria.andUsernameLike("%"+keyword+"%");
			com.allen.web.model.UserExample.Criteria criteria2 = example.createCriteria();
			criteria2.andIsValidEqualTo(EnumBool.YES.getCode());
			criteria2.andLoginnameLike("%"+keyword+"%");
			example.or(criteria2 );
		}
		example.setOrderByClause("loginname");
		return this.userMapper.selectByExample(page, example);
	}
	
//	@Override
//	public List<User> selectUserByOrgid(String orgid){
//		UserExample example = new UserExample();
//		example.createCriteria().andIsValidEqualTo(EnumBool.YES.getCode()).andOrgidEqualTo(orgid);
//		return this.userMapper.selectByExample(example);
//	}
}
