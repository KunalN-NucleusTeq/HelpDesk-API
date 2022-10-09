package com.helpDesk.HelpDesk.service;

import java.util.Set;

import javax.xml.bind.ValidationException;

import com.helpDesk.HelpDesk.model.Catagory;

public interface CatagoryService {
	
	public Catagory addCatagory(Catagory catagory) throws ValidationException;
	
	public Set<String> findSubCatagoriesByCatagoryName(String CatName);
	
	public void deleteCatagoryAndSubCatagoryById(Integer cid);
	 
}
