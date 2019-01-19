package com.amazingfour.crms.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by kennyho on 2016/1/12.
 */
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;

    private Long roleId;
    private String roleName;
    private String roleDescript;
    
    //***做 role --> permission 一对多处理
    private List<Menu> permissions = new LinkedList<Menu>();


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleDescript() {
        return roleDescript;
    }

    public void setRoleDescript(String roleDescript) {
        this.roleDescript = roleDescript;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	public List<Menu> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Menu> permissions) {
		this.permissions = permissions;
	}
    
    
}
