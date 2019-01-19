package com.amazingfour.crms.service;


import com.amazingfour.crms.domain.User;

import java.util.List;

/**
 * Created by kennyho on 2016/1/11.
 */
public interface UserService extends BaseService<User,Long> {
    public User login(User user);
    public boolean existUserByName(String userName);
    public boolean findUserRole(Long id);
    public boolean defriend(Long id);
    public boolean updatePassword(User user);
    public boolean removeBlack(Long id);
    public User findOneById(User user);
    public boolean existEmail(User user);
    public boolean bindEmail(User user);
    public boolean activated(User user);
    public int existUserEmail(User user);
    public boolean updateUserInifo(User user);
    public boolean saveEmailMes(User user);
    public List<Integer> findByRoleName(String roleName);
    //批量删除用户
    public int deleteBatch(List<String> userId);
    
    /**
     * 
     * @Title: login 
     * @Description: 重构：shiro的登陆方法
     * @param userName
     * @param passWord
     * @return 参数说明
     * @return User    返回类型
     */
    public User login(String userName ,String passWord);
	/** 
	 * @Title: updateByPrimaryKeySelective 
	 * @Description: 更新user
	 * @param user 参数说明
	 * @return void    返回类型
	 */
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
