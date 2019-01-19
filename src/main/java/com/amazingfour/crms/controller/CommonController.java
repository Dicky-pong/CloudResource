/**   
 * @Project: crms 
 * @Title: CommonController.java 
 * @Package com.amazingfour.crms.controller 
 * @Description: TODO 
 * @author pengwx 
 * @date 2018年4月17日 下午3:36:45 
 * @Copyright: 2018 年 研信科技. All rights reserved  
 * @version V1.0   
 */
package com.amazingfour.crms.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName CommonController  
 * @Description TODO 
 * @author pengwx 
 * @date 2018年4月17日  
 *   
 */
@Controller
@Scope(value="prototype")
@RequestMapping("/open/")
public class CommonController {
	
	/**
	 * 没有权限提示页面
	 * @return
	 */
	@RequestMapping(value="unauthorized",method=RequestMethod.GET)
	public ModelAndView unauthorized(){
		return new ModelAndView("common/unauthorized");
	}
	
	@RequestMapping(value="shiro",method=RequestMethod.GET)
	public ModelAndView shiro(){
		return new ModelAndView("shiro");
	}

}
