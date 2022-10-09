package com.helpDesk.HelpDesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpDesk.HelpDesk.model.Employee;

@Repository
@EnableJpaRepositories
public interface EmployeeRepositary extends JpaRepository<Employee, Integer> {

	@Query("Select e FROM Employee e where e.email=:email and e.password=:password")
	Employee findEmployeeByEmailOrPassword(@Param("email") String email,@Param("password") String password);

}
