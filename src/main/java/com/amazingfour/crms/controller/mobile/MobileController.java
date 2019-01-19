package com.amazingfour.crms.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazingfour.crms.domain.User;

@Controller
@RequestMapping("/mobile/")
public class MobileController {
	
	/**
	 * 进入移动端主页
	 * @param request
	 * @return
	 */
    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest request) {
    	
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/mobile/mobileIndex");
        return mav;
    }

}
