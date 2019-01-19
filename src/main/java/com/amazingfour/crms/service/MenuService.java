package com.amazingfour.crms.service;

import com.amazingfour.crms.domain.Menu;
import com.amazingfour.crms.domain.RoleMenu;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Dicky on 2016/2/3.
 */
public interface MenuService extends BaseService<Menu,Long> {
    List<Menu> getMenuById(Long roleId);
    
    public List<Menu> findByName(Map<String,Object> map);

    public void insertMenu(RoleMenu domain);

    public void updateMenu(RoleMenu domain);

    public void deleteMenu(RoleMenu domain);

	/** 
	 * @Title: findPermissionByUserId 
	 * @Description: 获取权限
	 * @param userId
	 * @return 参数说明
	 * @return Set<String>    返回类型
	 */
	Set<String> findPermissionByUserId(Long userId);

	public int countByName(Menu menu);
}
