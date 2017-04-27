package com.stock.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stock.comm.PageBean_easyui;
import com.stock.menu.dao.MenuDao;
import com.stock.pojo.Menu;

public class MenuService {
	private MenuDao dao = new MenuDao();
	
	public Map showMenu() {
		List menus = dao.showMenu();
		
		Map menuMap=new HashMap();
		
		for (int i = 0; i < menus.size(); i++) {
			Menu m = (Menu) menus.get(i);
			String father_num = m.getFather_num();
			List childrens = new ArrayList();
			if(null!=menuMap.get(father_num)){
				childrens = (List) menuMap.get(father_num);
			}
			childrens.add(m);
			menuMap.put(father_num, childrens);
		}
		return menuMap;
	}

	public List findMenu(Menu menu, PageBean_easyui pageBean) {
		return dao.findMenu(menu,pageBean);
	}

	public String inserted(Menu[] menu) {
		return dao.inserted(menu);
	}

	public String deleted(Menu[] menu) {
		return dao.deleted(menu);
	}

	public String updated(Menu[] menu) {
		return dao.updated(menu);
	}

}
