package com.helpDesk.HelpDesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpDesk.HelpDesk.model.UserRole;

public interface UserRoleRepositary extends JpaRepository<UserRole, Integer> {

}
