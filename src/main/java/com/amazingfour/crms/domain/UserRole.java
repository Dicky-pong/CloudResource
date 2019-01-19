/**   
 * @Project: crms 
 * @Title: UserRole.java 
 * @Package com.amazingfour.crms.domain 
 * @Description: TODO 
 * @author pengwx 
 * @date 2018年3月23日 上午11:46:12 
 * @Copyright: 2018 年 研信科技. All rights reserved  
 * @version V1.0   
 */
package com.amazingfour.crms.domain;

import java.io.Serializable;

/** 
 * @ClassName UserRole  
 * @Description TODO 
 * @author pengwx 
 * @date 2018年3月23日  
 *   
 */
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
    private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	
	public UserRole(Long userId, Long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}


    
}
