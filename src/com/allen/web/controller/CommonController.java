package com.allen.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.allen.core.generic.GenericController;
import com.allen.core.util.ApplicationUtils;
import com.allen.web.model.Activity;
import com.allen.web.service.activity.ActivityService;

import sun.util.calendar.CalendarUtils;


/**
 * 公共视图控制器
 * 
 * @author wenquan
 * @since 2015年7月29日17:32:44
 **/
@Controller
public class CommonController extends GenericController{
	
	Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Resource
	ActivityService activityService;
	
    /**
     * 首页
     * 
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(@ModelAttribute(value="keyword") String keyword, ModelMap map, HttpServletRequest request) {
    	List<Activity> activityList = activityService.selectListSelective(keyword);
    	Map<String, Map<String, List<Activity>>> activityMap = new LinkedHashMap<>();//map<year,map<month,list>>
    	int size = activityList.size();
    	int i = 0;
    	int startPoint=0,endPoint=0;
		Map<String, List<Activity>> subMap = new LinkedHashMap<>();//map<month,list>
		List<Activity> list = new ArrayList<>();
		while(endPoint<size){
			String year = ApplicationUtils.getYear(activityList.get(startPoint).getActivityDate());
    		String month = ApplicationUtils.getMonth(activityList.get(startPoint).getActivityDate());
			String nextYear = ApplicationUtils.getYear(activityList.get(endPoint).getActivityDate());
			String nextMonth = ApplicationUtils.getMonth(activityList.get(endPoint).getActivityDate());
			if (StringUtils.equals(year, nextYear)) {
				if (StringUtils.equals(month, nextMonth)) {
					list.add(activityList.get(endPoint));
					endPoint++;
				}else{
					subMap.put(month, list);
					startPoint = endPoint;
					list = new ArrayList<>();
					list.add(activityList.get(endPoint));
					endPoint++;
				}
			}else{
				subMap.put(month, list);
				activityMap.put(year, subMap);
				subMap = new LinkedHashMap<>();
				list = new ArrayList<>();
				startPoint = endPoint;
				list.add(activityList.get(endPoint));
				endPoint++;
			}
			
		}
		subMap.put(ApplicationUtils.getMonth(activityList.get(size-1).getActivityDate()), list);
    	activityMap.put(ApplicationUtils.getYear(activityList.get(size-1).getActivityDate()), subMap);
    	map.put("activityMap", activityMap);
        return "/index";
    }
    @RequestMapping("/admin/index")
    public String adminIndex(ModelMap map, HttpServletRequest request) {
    	return "/adminIndex";
    }

    @RequestMapping("/admin/setPassword")
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
                new CookieLocaleResolver().setLocale(request, response, locale);
            } else if (langType.equals("en")) {
                Locale locale = new Locale("en", "US");
                new CookieLocaleResolver().setLocale(request, response, locale);
            } else
            	new CookieLocaleResolver().setLocale(request, response, LocaleContextHolder.getLocale());
            return null;
    }
    
}