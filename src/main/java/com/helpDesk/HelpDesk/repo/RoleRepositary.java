package com.helpDesk.HelpDesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helpDesk.HelpDesk.model.Role;

public interface RoleRepositary extends JpaRepository<Role, Integer> {
	
	Role findByRoleName(String role);
	
	@Query("select r from Role r Join UserRole ur on r.roleId = ur.roleId where ur.emailUser=:email")
	Role findRoleByUserRoleId(@Param("email") String email);

}
