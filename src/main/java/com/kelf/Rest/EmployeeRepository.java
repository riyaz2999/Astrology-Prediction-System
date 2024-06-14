package com.kelf.Rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>
{
	@Query("select e from EmployeeEntity e where e.email=?1 and e.password=?2")
	public EmployeeEntity checkemployeelogin(String uname, String pwd);
} 