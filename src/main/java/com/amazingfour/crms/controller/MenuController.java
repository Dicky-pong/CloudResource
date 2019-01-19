package com.amazingfour.crms.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazingfour.common.utils.PageUtil;
import com.amazingfour.common.utils.StringUtils;
import com.amazingfour.crms.domain.Menu;
import com.amazingfour.crms.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Resource
	private MenuService menuService;

	// 从数据库中获取所有菜单列表
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "page", required = false) String page,
			Menu menu, HttpServletRequest request)
			throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> params = new HashMap<String, Object>();

		int pageSize = 5; // 页容量

		if (page == null || page == "") {

			page = "1";
		}
		
		Map<String, Object> map = new HashMap<String, Object>(); // 使用Map传值到mapper处理
		map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
		map.put("size", pageSize);
		if (StringUtils.isNotBlank(menu.getMenuName())) {
			map.put("menuName", menu.getMenuName());
			params.put("menuName", menu.getMenuName());
		}

		List<Menu> menuList = menuService.findByName(map); // 查询符合条件的所有Role角色
		int total = menuService.countByName(menu); // 符合条件的总记录数

		String pageCode = PageUtil.getPagation("menu/list.htm", params, total,
				Integer.parseInt(page), pageSize);
		mav.addObject("pageCode", pageCode);
		mav.addObject("menuList", menuList);
		mav.setViewName("menu/menuMain");
		return mav;
	}
}
