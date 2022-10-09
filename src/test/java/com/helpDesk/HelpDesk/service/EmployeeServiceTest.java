package com.helpDesk.HelpDesk.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.helpDesk.HelpDesk.Dto.LoginDto;
import com.helpDesk.HelpDesk.Dto.LoginResponseDto;
import com.helpDesk.HelpDesk.Dto.SignInDto;
import com.helpDesk.HelpDesk.exception.UnauthorisedExpection;
import com.helpDesk.HelpDesk.model.Employee;
import com.helpDesk.HelpDesk.model.Role;
import com.helpDesk.HelpDesk.model.UserRole;
import com.helpDesk.HelpDesk.repo.EmployeeRepositary;
import com.helpDesk.HelpDesk.repo.RoleRepositary;
import com.helpDesk.HelpDesk.repo.UserRoleRepositary;
import com.helpDesk.HelpDesk.service.Impl.EmployeeServiceimpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepositary employeeRepositary;
	
	@Mock
	private UserRoleRepositary userRoleRepositary;
	
	@Mock
	private RoleRepositary roleRepositary;
	
	@Autowired
	@InjectMocks
	private EmployeeServiceimpl empServiceimpl;
	
	private Employee employee1;
	private Employee employee2;
	
	private List<Employee> lEmployees;
	private UserRole userRole;
	private Role role;
	private LoginDto loginDto;
	
	@BeforeEach
	public void setUp() {
		lEmployees = new ArrayList<>();
		employee1 = new Employee(1,"Kunal","Namdev","email5","password","India","mp","city","admin");
		employee2 = new Employee(1,"Sakshi","Gupta","email1","password","India","mp","city","employee");
		userRole = new UserRole(1, "email3", 1);
		role = new Role(1,"admin");
		loginDto = new LoginDto("email","password");
		lEmployees.add(employee1);
		lEmployees.add(employee2);
	}
	
	@Test
	public void givenEmployeeToAddShouldReturnAddedSignInDto() throws ValidationException{
		
	     //stubbing
		when(roleRepositary.findByRoleName(Mockito.any())).thenReturn(role);
	     when(employeeRepositary.save(Mockito.any())).thenReturn(employee1);
	     when(userRoleRepositary.save(Mockito.any())).thenReturn(userRole);
	     
	     empServiceimpl.employeeRegistation(employee1);
	     verify(employeeRepositary,times(1)).save(Mockito.any());
	     verify(userRoleRepositary,times(1)).save(Mockito.any());
	}
	
	@Test
	public void givenLoginDtoToShouldBeLoginReturnLoginOutDto() throws UnauthorisedExpection {
		when(roleRepositary.findRoleByUserRoleId(Mockito.any())).thenReturn(role);
		when(employeeRepositary.findEmployeeByEmailOrPassword(Mockito.any(),Mockito.any())).thenReturn(employee1);
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setMessage("Admin Login SignIn Succssefully");
		
		LoginResponseDto lrd =  empServiceimpl.loginEmployeeByEmailOrPassword(loginDto);
		assertEquals(loginResponseDto.getMessage(), lrd.getMessage());

	}
	
	@Test
	public void givenLoginDtoToShouldBeLoginReturnLoginOutDtoNegative() throws UnauthorisedExpection {
		try {
		empServiceimpl.loginEmployeeByEmailOrPassword(loginDto);
		}
		catch(UnauthorisedExpection ex) {
		assertEquals("Invalid Credential",ex.getMessage());
		}

	}
	
	@AfterEach
	public void tearDown() {
		employee1 = employee2 = null;
		lEmployees = null;
		userRole = null;
		role = null;
		loginDto = null;
	}

}
