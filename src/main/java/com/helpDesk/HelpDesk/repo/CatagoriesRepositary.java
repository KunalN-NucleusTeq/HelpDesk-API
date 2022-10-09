package com.helpDesk.HelpDesk.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helpDesk.HelpDesk.model.Catagories;

public interface CatagoriesRepositary extends JpaRepository<Catagories, Integer> {
	
	@Query("select c.subCatagoriesName From Catagories c JOIN Catagory cat on c.catId = cat.catId where cat.catName=:catName")
	public Set<String> findCatagoriesByCatagoryName(@Param("catName") String catName);

	public Set<Catagories> findByCatId(Integer catId);

}
