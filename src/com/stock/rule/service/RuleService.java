package com.stock.rule.service;

import java.util.List;

import org.junit.Test;

import com.stock.comm.PageBean_easyui;
import com.stock.comm.TreeUtil;
import com.stock.pojo.Rule;
import com.stock.rule.dao.RuleDao;

public class RuleService {
	private RuleDao dao = new RuleDao();
	private TreeUtil treeUtil = new TreeUtil();
	public List find(Rule rule, PageBean_easyui pageBean) {
		return dao.find(rule,pageBean);
	}
	public String inserted(Rule[] rule) {
		return dao.inserted(rule);
	}
	public String deleted(Rule[] rule) {
		return dao.deleted(rule);
	}
	public String updated(Rule[] rule) {
		return dao.updated(rule);
	}
	public String powerSet(String roleid, String menuIds) {
		return dao.powerSet(roleid,menuIds);
	}
	public String menuTree(Rule rule) {
		List list = dao.menuTree(rule);
		String jsonStr = treeUtil.backComboTreeTreeRole(list);
		return jsonStr;
	}
}
