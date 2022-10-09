package com.helpDesk.HelpDesk.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpDesk.HelpDesk.model.Tickit;

@Repository
public interface TickitRepository extends JpaRepository<Tickit, Integer>{

	@Query("select t from Tickit t inner join Tickit_Employee te on t.tickitId = te.tickitId where te.empId =:eid")
	List<Tickit> listOfTickitByEmployeeId(Integer eid);

	@Modifying
	@Query(nativeQuery = true, value = "delete t,te from tickit t inner join tickit_employee te on t.tickit_id = te.tickit_id where t.tickit_id =:tid")
	Integer deleteTickitByTickitId(@Param("tid") Integer tid);
}
