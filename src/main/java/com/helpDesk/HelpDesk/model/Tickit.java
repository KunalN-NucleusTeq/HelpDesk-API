package com.helpDesk.HelpDesk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import com.helpDesk.HelpDesk.RoleEnum.PriorityEnum;
import com.helpDesk.HelpDesk.RoleEnum.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tickit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer tickitId;
	@Column(nullable = false)
	private String catagory;
	private String subCatagory;
	@ColumnDefault("'Open'")
	private String status = StatusEnum.OPEN.toString();
	@ColumnDefault("'Low'")
	private String priority = PriorityEnum.LOW.toString();
	private String shortDescription;
	@Column(nullable = false)
	private Date isCreated;
	private Date isResolved;
	@Transient
	private Integer empId;

}
