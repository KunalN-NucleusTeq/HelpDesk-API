package com.helpDesk.HelpDesk.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpDesk.HelpDesk.Dto.EmployeeDto;
import com.helpDesk.HelpDesk.Dto.LoginDto;
import com.helpDesk.HelpDesk.Dto.LoginResponseDto;
import com.helpDesk.HelpDesk.Dto.SignInDto;
import com.helpDesk.HelpDesk.RoleEnum.RoleEnum;
import com.helpDesk.HelpDesk.exception.UnauthorisedExpection;
import com.helpDesk.HelpDesk.model.Employee;
import com.helpDesk.HelpDesk.model.Role;
import com.helpDesk.HelpDesk.model.UserRole;
import com.helpDesk.HelpDesk.repo.EmployeeRepositary;
import com.helpDesk.HelpDesk.repo.RoleRepositary;
import com.helpDesk.HelpDesk.repo.UserRoleRepositary;
import com.helpDesk.HelpDesk.service.EmployeeService;

@Service
public class EmployeeServiceimpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepositary employeeRepositary;
	
	@Autowired
	private RoleRepositary roleRepositary;
	
	@Autowired
	private UserRoleRepositary userRoleRepositary;

	@Override
	public SignInDto employeeRegistation(Employee employee) throws ValidationException {
		Role role = roleRepositary.findByRoleName(employee.getRole());
		if (role == null) {
			throw new ValidationException("Please select the role of Employee");
		}
		SignInDto signInDto = new SignInDto();
		UserRole userRole = new UserRole();
		try {
			Employee employee2 = employeeRepositary.save(employee);
			userRole.setEmailUser(employee2.getEmail());
			userRole.setRoleId(role.getRoleId());
			userRoleRepositary.save(userRole);
			signInDto.setEmail(employee2.getEmail());
			signInDto.setEmpId(employee2.getEmpId());
			signInDto.setPassword(employee2.getPassword());
			signInDto.setMessage("SignIn Successfully");
		} catch (Exception ex) {
			throw new ValidationException("Email already exits");
		}

		return signInDto;
	}

	@Override
	public LoginResponseDto loginEmployeeByEmailOrPassword(LoginDto loginDto) throws UnauthorisedExpection{
		Employee employee = employeeRepositary.findEmployeeByEmailOrPassword(loginDto.getEmail(),loginDto.getPassword());
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		if(Objects.isNull(employee)) {
			throw new UnauthorisedExpection("Invalid Credential");
		}
		else {
			Role role  = roleRepositary.findRoleByUserRoleId(employee.getEmail());
			employee.setRole(role.getRoleName());
		}
		if(employee.getRole().equalsIgnoreCase(RoleEnum.ADMIN.toString())) {
			loginResponseDto.setSignupStatus(true);
			loginResponseDto.setMessage("Admin Login SignIn Succssefully");
		}
		else if(employee.getRole().equalsIgnoreCase(RoleEnum.EMPLOYEE.toString())) {
			loginResponseDto.setSignupStatus(true);
			loginResponseDto.setMessage("Employee SignIn Successfully");
		}
		return loginResponseDto;
	}
	
	
	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepositary.findAll();
		
		List<EmployeeDto> listEmployeeDto = new ArrayList<>();
			for(Employee emp : employees) {
				EmployeeDto e = new EmployeeDto();
				Role role  = roleRepositary.findRoleByUserRoleId(emp.getEmail());
				e.setEmail(emp.getEmail());
				e.setEmpId(emp.getEmpId());
				e.setRole(role.getRoleName());
				e.setFname(emp.getFname());
				e.setLname(emp.getLname());
				e.setCountry(emp.getCountry());
				e.setState(emp.getState());
				e.setCity(emp.getCity());
				listEmployeeDto.add(e);
			}
		return listEmployeeDto;
	}

}
