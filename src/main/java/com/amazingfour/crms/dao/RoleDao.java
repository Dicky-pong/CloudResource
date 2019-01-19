package com.amazingfour.crms.dao;

import com.amazingfour.crms.bo.RolePermissionAllocationBo;
import com.amazingfour.crms.domain.Role;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by kennyho on 2016/1/13.
 */
@Repository("roleDao")
public interface RoleDao extends BaseDao<Role,Long> {


    //查询数量
    public int getCountByName(Role domain);
    //增
    public void insert(Role domain);
    //删
    public int deleteRoleById(Long id);
    //改基本信息
    public void updateRoleBaseInfo(Role domain);
    //修改权限信息
    void updateRoleRights(Role role);
    //查询是否存在相同名字的角色
    public int existRoleByName(String roleName);
	/** 
	 * @Title: findRoleByUserId 
	 * @Description: 重构:用户对应角色
	 * @param userId
	 * @return 参数说明
	 * @return Set<String>    返回类型
	 */
	public Set<String> findRoleByUserId(Long userId);
	
	/**
	 * 
	 * @Title: findRoleAndPermission 
	 * @Description: 权限分配的分页查询  
	 * @param roleName
	 * @return 参数说明
	 * @return RolePermissionAllocationBo    返回类型
	 */
	public List<RolePermissionAllocationBo> findRoleAndPermission(String roleName);

}
