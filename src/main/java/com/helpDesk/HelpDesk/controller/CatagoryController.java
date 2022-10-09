package com.helpDesk.HelpDesk.controller;

import java.util.Set;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpDesk.HelpDesk.model.Catagory;
import com.helpDesk.HelpDesk.service.CatagoryService;

@RestController
@RequestMapping("/employee")
public class CatagoryController {
	
	@Autowired
	private CatagoryService catagoryService;
	
	@PostMapping("/addCatagory")
	public Catagory addCatagoryAndSubCatagories(@RequestBody Catagory catagory) throws ValidationException {
		return catagoryService.addCatagory(catagory);
	}
	
	@GetMapping("/catagory/{catName}")
	public Set<String> getSubCatagoriesByCatagoryName(@PathVariable("catName") String catName){
		return catagoryService.findSubCatagoriesByCatagoryName(catName);
	}
	
	@DeleteMapping("/deleteCatagory/{cid}")
	public void deleteCatagoryAndSubCatagoriesById(@PathVariable Integer cid) {
		 catagoryService.deleteCatagoryAndSubCatagoryById(cid);
	}
	
}
