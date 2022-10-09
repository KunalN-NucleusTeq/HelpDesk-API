package com.helpDesk.HelpDesk.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Catagory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catId;
	@Column(name="cat_name",nullable = false,unique = true)
	private String catName;
	@Transient
	private Set<Catagories> catagories = new HashSet<>();

}
