package com.helpDesk.HelpDesk.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpDesk.HelpDesk.model.Tickit_Employee;

public interface Tickit_EmployeeRepository extends JpaRepository<Tickit_Employee, Integer> {

	Optional<Tickit_Employee> findBytickitId(Integer tid);

}
