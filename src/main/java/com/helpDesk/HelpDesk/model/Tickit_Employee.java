package com.helpDesk.HelpDesk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.Modifying;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tickit_Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer ticEmpId;
	private Integer tickitId;
	private Integer empId;

}
