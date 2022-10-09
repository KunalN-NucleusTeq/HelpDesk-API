package com.helpDesk.HelpDesk.service.Impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpDesk.HelpDesk.model.Catagories;
import com.helpDesk.HelpDesk.model.Catagory;
import com.helpDesk.HelpDesk.repo.CatagoriesRepositary;
import com.helpDesk.HelpDesk.repo.CatagoryRepositary;
import com.helpDesk.HelpDesk.service.CatagoryService;

@Service
public class CatagoryServiceImpl implements CatagoryService {
	
	@Autowired
	private CatagoryRepositary catagoryRepositary;
	
	@Autowired
	private CatagoriesRepositary catagoriesRepositary;

	@Override
	public Catagory addCatagory(Catagory catagory) throws ValidationException {

		Set<Catagories> catagories = new HashSet<>();
		
		Catagory response  = new Catagory();
		try {
		Catagory cat = catagoryRepositary.save(catagory);

		catagory.getCatagories().stream().map(c -> {
			c.setCatId(cat.getCatId());
			return c;
		}).collect(Collectors.toSet());

		catagories.addAll(cat.getCatagories());

		catagoriesRepositary.saveAll(catagories);
		
		response.setCatName(cat.getCatName());
		response.setCatagories(cat.getCatagories());
		response.setCatId(cat.getCatId());
		}catch (Exception e) {
			throw new ValidationException("Catagory already exists");
		}

		return response;
	}

	@Override
	public Set<String> findSubCatagoriesByCatagoryName(String CatName) {
		Set<String> catagories = catagoriesRepositary.findCatagoriesByCatagoryName(CatName);
		return catagories;
	}

	@Override
	public void deleteCatagoryAndSubCatagoryById(Integer cid) {
		catagoryRepositary.deleteCatagoryAndSubCatagoriesById(cid);
	}


}
