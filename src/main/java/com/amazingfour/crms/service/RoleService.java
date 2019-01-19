package com.amazingfour.crms.service;

import com.amazingfour.crms.bo.RolePermissionAllocationBo;
import com.amazingfour.crms.domain.Menu;
import com.amazingfour.crms.domain.Role;

import java.util.List;
import java.util.Set;

/**
 * Created by kennyho on 2016/1/13.
 */
public interface RoleService extends BaseService<Role,Long> {
    //public List<Role> findByUserId(Long userId);
    public List<Object> listAllMenu();
    public List<Object> listAllMenuById(List<Menu> menu);
    public List<Menu> listSubMenu(String menuId);
    public boolean existRoleByName(String roleName);
	/** 
	 * @Title: findRoleByUserId 
	 * @Description: 重构：找到用户对应角色
	 * @param userId
	 * @return 参数说明
	 * @return Set<String>    返回类型
	 */
	public Set<String> findRoleByUserId(Long userId);
	
	public List<RolePermissionAllocationBo> findRoleAndPermission(String roleName);

}
