package com.tjhx.service.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.security.MenuJpaDao;
import com.tjhx.entity.security.Menu;

@Service
@Transactional(readOnly = true)
public class MenuManager {
	@Resource
	private MenuJpaDao menuJpaDao;

	/**
	 * 根据用户信息取得其访问功能菜单信息
	 * 
	 * @return
	 */
	public List<Menu> getMenuTreeInfoByUser() {
		// TODO
		List<Menu> menuList = (List<Menu>) menuJpaDao.findAll();
		return null;

	}

	/**
	 * 构建整个菜单树结构
	 */
	private List<Menu> buildMenuInfoTree(List<Menu> _reList, Menu dbParentNode) {
		List<Menu> _reList=new ArrayList<Menu>();
		for (Menu dbNode : dbParentNode.getSubMenus()) {
			if (dbNode != null) {
				Menu _reMenu = new Menu();

				_reMenu.setId(dbNode.getId());
				_reMenu.setName(dbNode.getName());
				
					

				_reList.add(_reMenu);

				buildMenuNodeInfoTree(menuTreeNode, dbNode, permitList, roleID);
			}
		}
	}
}
