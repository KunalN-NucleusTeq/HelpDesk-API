package com.helpDesk.HelpDesk.controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpDesk.HelpDesk.Dto.EmployeeDto;
import com.helpDesk.HelpDesk.Dto.LoginDto;
import com.helpDesk.HelpDesk.Dto.LoginResponseDto;
import com.helpDesk.HelpDesk.Dto.SignInDto;
import com.helpDesk.HelpDesk.exception.UnauthorisedExpection;
import com.helpDesk.HelpDesk.model.Employee;
import com.helpDesk.HelpDesk.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/addEmployee")
	public ResponseEntity<SignInDto> addEmployee(@RequestBody Employee employee) throws ValidationException{
		SignInDto signInDto = employeeService.employeeRegistation(employee);
		return new ResponseEntity<SignInDto>(signInDto,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> loginEmployee(@RequestBody LoginDto loginDto) throws UnauthorisedExpection{
		LoginResponseDto loginResponseDto = employeeService.loginEmployeeByEmailOrPassword(loginDto);
		return new ResponseEntity<LoginResponseDto>(loginResponseDto,HttpStatus.FOUND);
	}
	
	@GetMapping("/allEmployee")
	public List<EmployeeDto> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
		
}
