package com.allen.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.allen.core.generic.GenericController;


/**
 * 公共视图控制器
 * 
 * @author wenquan
 * @since 2015年7月29日17:32:44
 **/
@Controller
public class CommonController extends GenericController{
	
    /**
     * 首页
     * 
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String adminIndex(ModelMap map, HttpServletRequest request) {
        return "/index";
    }

    @RequestMapping("/setPassword")
	public String setPassword(ModelMap map) {
		map.put("userId", this.getLoginUser().getUserId());
		return "/setPassword";
	}
    
    @RequestMapping("/lang")
    @ResponseBody
    public String lang(HttpServletRequest request,HttpServletResponse response) {
            String langType = request.getParameter("langType");
            if (langType.equals("zh")) {
                Locale locale = new Locale("zh", "CN");
                //request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
                new CookieLocaleResolver().setLocale(request, response, locale);
            } else if (langType.equals("en")) {
                Locale locale = new Locale("en", "US");
                //request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
                new CookieLocaleResolver().setLocale(request, response, locale);
            } else
            	new CookieLocaleResolver().setLocale(request, response, LocaleContextHolder.getLocale());
                //request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, LocaleContextHolder.getLocale());
            return null;
    }
}