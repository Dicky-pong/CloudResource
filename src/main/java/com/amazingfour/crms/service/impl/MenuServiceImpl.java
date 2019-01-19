package com.amazingfour.crms.service.impl;


import com.amazingfour.crms.dao.MenuDao;
import com.amazingfour.crms.domain.Menu;
import com.amazingfour.crms.domain.RoleMenu;
import com.amazingfour.crms.service.MenuService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dicky on 2016/2/3.
 */
@Service("menuService")
public class MenuServiceImpl extends AbstractService<Menu,Long> implements MenuService {
    @Resource
    private MenuDao menuDao;

    @Resource
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public List<Menu> getMenuById(Long roleId) {
        return menuDao.getMenuById(roleId);
    }

    @Override
    public void insertMenu(RoleMenu domain) {
         menuDao.insertMenu(domain);
    }

    @Override
    public void updateMenu(RoleMenu domain) {
        menuDao.updateMenu(domain);
    }

    @Override
    public void deleteMenu(RoleMenu domain) {
        menuDao.deleteMenu(domain);
    }

	@Override
	public Set<String> findPermissionByUserId(Long userId) {
		return menuDao.findPermissionByUserId(userId);
	}

	@Override
	public List<Menu> findByName(Map<String,Object> map) {
		return menuDao.findByName(map);
	}

	@Override
	public int countByName(Menu menu) {
		return menuDao.countByName(menu);
	}

}
