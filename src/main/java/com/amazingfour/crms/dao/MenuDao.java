package com.amazingfour.crms.dao;

import com.amazingfour.crms.domain.Menu;
import com.amazingfour.crms.domain.Role;
import com.amazingfour.crms.domain.RoleMenu;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dicky on 2016/1/21.
 */
@Repository("menuDao")
public interface MenuDao extends BaseDao<Menu, Long> {
	public List<Menu> listAllMenu();

	public List<Menu> listSubMenuByParentId(Integer parentId);

	public List<Menu> getMenuById(Long roleId);

	public void insertMenu(Menu menu);

	public void updateMenu(RoleMenu roleMenu);

	public void deleteMenu(RoleMenu roleMenu);

	public List<Menu> listSubMenu(String menuId);

	public void insertMenu(RoleMenu domain);

	/**
	 * @Title: findPermissionByUserId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param userId
	 * @return 参数说明
	 * @return Set<String> 返回类型
	 */
	public Set<String> findPermissionByUserId(Long userId);

	/**
	 * 根据菜单名模糊寻找菜单
	 * 
	 * @param menuName
	 * @return
	 */
	public List<Menu> findByName(Map<String,Object> map);

	public int countByName(Menu menu);
}
