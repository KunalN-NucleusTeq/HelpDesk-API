package com.helpDesk.HelpDesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	@Column(name = "fname", nullable = false)
	private String fname;
	@Column(name = "lname", nullable = false)
	private String lname;
	@Column(name = "email", nullable = false,unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "country", nullable = false)
	private String country;
	@Column(name = "state", nullable = false)
	private String state;
	@Column(name = "city", nullable = false)
	private String city;
	@Transient
	private String role;
}
