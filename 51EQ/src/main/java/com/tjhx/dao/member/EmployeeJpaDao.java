package com.tjhx.dao.member;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.member.Employee;

public interface EmployeeJpaDao extends CrudRepository<Employee, Integer> {

	@Query("select e from Employee e, Organization org where e.organization = org and e.empType = '2' and org.id = :orgId")
	public List<Employee> getTmpEmployeeListByOrgId(@Param("orgId") String orgId);
}
