package com.amazingfour.crms.dao;

import com.amazingfour.crms.domain.User;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by kennyho on 2016/1/11.
 */
@Repository("userDao")
public interface UserDao extends BaseDao<User,Long>{
    public User login(User user);
    public int existUserByName(String userName);
    public int findUserRole(Long id);
    public int defriend(Long id);
    public int updatePassword(User user);
    public int removeBlack(Long id);
    public User findOneById(User user);
    public int bindEmail(User user);
    public int updateUserInfo(User user);
    public int activated(User user);
    public int saveEmailMes(User user);
    public List<Integer> findByRoleName(String roleName);
    //批量删除文件
    public int deleteBatch(List<String> userId);
    
	public User shiroLogin(Map<String, Object> map);
	
	public int updateByPrimaryKeySelective(User user);
	/** 
	 * @Title: findUserIdByRoleId 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param roleId
	 * @return 参数说明
	 * @return List<Long>    返回类型
	 */
	public List<Long> findUserIdByRoleId(String roleId);

}
