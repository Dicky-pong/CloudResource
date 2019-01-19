package com.amazingfour.crms.domain;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by kennyho on 2016/1/11.
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	//0:禁止登录
	public static final Long _0 = new Long(0);
	//1:有效
	public static final Long _1 = new Long(1);
	
	private Long userId;
	
	private String userName;
	
	private String password;
	
	private String userDescript;
	
	private Byte userState;
	
	private String verifyCode;
	
	private String userEmail;
	
	private Date outDate;
	
	private Byte activated;
	
	private String emailKey;
	
	private Date last_login_time;
	
	private String imgUrl;
	
	private Long telPhone;
	
	private Date createTime;
	
	private String isComplete;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

   @Transient //不需要持久到DB的属性使用该注解
    public String getVerifyCode(){return verifyCode;}
    public void setVerifyCode(String verifyCode){this.verifyCode=verifyCode;}


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserDescript() {
        return userDescript;
    }

    public void setUserDescript(String userDescript) {
        this.userDescript = userDescript;
    }

    public Byte getUserState() {
        return userState;
    }

    public void setUserState(Byte userState) {
        this.userState = userState;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Byte getActivated() {
        return activated;
    }

    public void setActivated(Byte activated) {
        this.activated = activated;
    }

    public String getEmailKey() {
        return emailKey;
    }

    public void setEmailKey(String emailKey) {
        this.emailKey = emailKey;
    }

    
    public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(Long telPhone) {
        this.telPhone = telPhone;
    }

    public User() {
    	
    }
 
	public User(User user) {
		super();
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.userDescript = user.getUserDescript();
		this.userState = user.getUserState();
		this.verifyCode = user.getVerifyCode();
		this.userEmail = user.getUserEmail();
		this.activated = user.getActivated();
		this.emailKey = user.getEmailKey();
		this.last_login_time = user.getLast_login_time();
		this.imgUrl = user.getImgUrl();
		this.telPhone = user.getTelPhone();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", userDescript=" + userDescript
				+ ", userState=" + userState + ", verifyCode=" + verifyCode
				+ ", userEmail=" + userEmail + ", outDate=" + outDate
				+ ", activated=" + activated + ", emailKey=" + emailKey
				+ ", last_login_time=" + last_login_time + ", imgUrl=" + imgUrl
				+ ", telPhone=" + telPhone + ", createTime=" + createTime
				+ ", isComplete=" + isComplete + "]";
	}
    
    
}
