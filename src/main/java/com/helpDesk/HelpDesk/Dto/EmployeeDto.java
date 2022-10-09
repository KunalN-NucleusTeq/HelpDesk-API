package com.helpDesk.HelpDesk.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {
	
	private Integer empId;
	private String fname;
	private String lname;
	private String email;
	private String city;
	private String state;
	private String country;
	private String role;

}
