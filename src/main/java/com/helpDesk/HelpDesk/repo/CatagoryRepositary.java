package com.helpDesk.HelpDesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.helpDesk.HelpDesk.model.Catagory;

@EnableJpaRepositories
public interface CatagoryRepositary extends JpaRepository<Catagory, Integer> {
	
	@Query(nativeQuery = true, value = "Delete cats, cat from Catagory cat Join Catagories cats on cat.cat_id = cats.cat_id where cat.cat_id=:cid")
	public void deleteCatagoryAndSubCatagoriesById(@Param("cid") Integer cid);

	public Catagory findCatagoryByCatId(Integer catId);
	
	
	

}
