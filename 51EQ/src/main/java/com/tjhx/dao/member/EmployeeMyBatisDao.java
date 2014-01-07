package com.tjhx.dao.member;

import java.util.List;

import com.tjhx.entity.member.Employee;

public interface EmployeeMyBatisDao {

	public List<Employee> getEmployeeListByOrgId(String orgId);

	public List<Employee> getEmployeeList();

	public List<Employee> getTmpEmployeeListByOrgId(String orgId);

	public List<Employee> getTmpEmployeeByOrgId_WorkFlg(String orgId);

	public void deleteEmployeeByEmpType(String empType);

}
