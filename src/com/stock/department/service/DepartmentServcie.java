package com.stock.department.service;

import java.util.List;

import com.stock.comm.PageBean_easyui;
import com.stock.department.dao.DepartmentDao;
import com.stock.pojo.Department;

public class DepartmentServcie {
	private DepartmentDao dao = new DepartmentDao();
	public List findDepartment(Department department, PageBean_easyui pageBean) {
		return dao.findDepartment(department,pageBean);
	}

	public String inserted(Department[] department) {
		return dao.inserted(department);
	}

	public String deleted(Department[] department) {
		return dao.deleted(department);
	}

	public String updated(Department[] department) {
		return dao.updated(department);
	}

}
