package com.helpDesk.HelpDesk.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.helpDesk.HelpDesk.Dto.EmployeeDto;
import com.helpDesk.HelpDesk.Dto.LoginDto;
import com.helpDesk.HelpDesk.Dto.LoginResponseDto;
import com.helpDesk.HelpDesk.Dto.SignInDto;
import com.helpDesk.HelpDesk.exception.UnauthorisedExpection;
import com.helpDesk.HelpDesk.model.Employee;

public interface EmployeeService {
	
	public SignInDto employeeRegistation(Employee employee) throws ValidationException;
	
	public LoginResponseDto loginEmployeeByEmailOrPassword(LoginDto loginDto) throws UnauthorisedExpection;
	
	public List<EmployeeDto> getAllEmployees();

}
