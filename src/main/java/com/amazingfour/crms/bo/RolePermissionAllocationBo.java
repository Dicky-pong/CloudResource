package com.amazingfour.crms.bo;

import java.io.Serializable;
/**
 * 权限分配 查询列表BO
 * @author zhou-baicheng
 *
 */
public class RolePermissionAllocationBo implements Serializable {
	private static final long serialVersionUID = 1L;
	//角色ID
	private Long role_id;
	//角色描述
	private String role_descript;
	//角色Name
	private String role_name;
	//权限Name列转行，以,分割
	private String permissionNames;
	//权限Id列转行，以‘,’分割
	private String permissionIds;

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getRole_descript() {
		return role_descript;
	}

	public void setRole_descript(String role_descript) {
		this.role_descript = role_descript;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getPermissionNames() {
		return permissionNames;
	}

	public void setPermissionNames(String permissionNames) {
		this.permissionNames = permissionNames;
	}

	public String getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}
	
	
}
