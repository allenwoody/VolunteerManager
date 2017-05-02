package com.allen.core.generic;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.allen.core.common.Const;
import com.allen.web.model.User;
import com.allen.web.model.UserInSession;
import com.allen.web.service.user.UserService;


/**
 * 控制器基类
 * 
 * @author wenquan
 * @since 2015年7月29日17:32:44
 **/
@Controller
public class GenericController implements ApplicationContextAware {  
	@Resource
	UserService userService;
	
    protected ApplicationContext applicationContext;
    
    private String imgPath;
    
    private String incPath;
    
    private String homepageFilePath;
    
	/**
	 * @Method: setApplicationContext
	 * @Description: 获得springbean
	 * @param arg0
	 * @throws BeansException
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

	
	public Session getSession() {
		
		
//		HttpSession session = null;
//		try {
//			session = getRequest().getSession(true);
//		} catch (Exception e) {
//		}
		Subject subject = SecurityUtils.getSubject();  
		Session session = subject.getSession();
		return session;
	}

	public HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}
	
	public UserInSession getLoginUser() throws AuthorizationException {
		UserInSession userInSession = (UserInSession) getSession().getAttribute(Const.UserInSession);
		if (userInSession == null){
			Subject subject = SecurityUtils.getSubject();  
			PrincipalCollection principals = subject.getPrincipals();
			String username = String.valueOf(principals.getPrimaryPrincipal());
			final User user = userService.selectByLoginName(username);
			if(null==user){
				throw new AuthorizationException();
			}
			userInSession = new UserInSession();
			userInSession.setLoginName(user.getLoginname());
			userInSession.setUsername(user.getUsername());
			userInSession.setUserId(user.getUserId());
			this.getSession().setAttribute(Const.UserInSession, userInSession);
		}
		return userInSession;
	}


	public String getImgPath() {
		if (StringUtils.isBlank(imgPath)) {
			imgPath = this.getRequest().getServletContext().getRealPath("/")+"upload\\images\\";
		}
		return imgPath;
	}
	
	public String getHomepageFilePath() {
		if (StringUtils.isBlank(homepageFilePath)) {
			homepageFilePath = this.getRequest().getServletContext().getRealPath("/")+"upload\\homepagefile\\";
		}
		return homepageFilePath;
	}

	public String getIncPath() {
		if (StringUtils.isBlank(incPath)) {
			incPath = this.getRequest().getServletContext().getRealPath("/")+"upload\\inc\\";
		}
		return incPath;
	}
}