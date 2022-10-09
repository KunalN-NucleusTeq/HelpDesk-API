package com.helpDesk.HelpDesk.controller;

import java.util.List;

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

import com.helpDesk.HelpDesk.exception.UnauthorisedExpection;
import com.helpDesk.HelpDesk.model.Tickit;
import com.helpDesk.HelpDesk.service.TickitService;

@RestController
@RequestMapping("/")
public class TickitController {
	
	@Autowired
	private TickitService tickitService;
	
	
	@PostMapping("employee/tickitCreate")
	public ResponseEntity<Tickit> createTickitByEmployee(@RequestBody Tickit tickit) throws ValidationException{
		return new ResponseEntity<Tickit>(tickitService.createTickit(tickit),HttpStatus.CREATED);
	}
	
	@PutMapping("admin/tickitUpdate/{tid}")
	public ResponseEntity<Tickit> updateTickitByAdmin(@PathVariable("tid") Integer tid,@RequestBody Tickit tickit) throws UnauthorisedExpection{
		return new ResponseEntity<Tickit>(tickitService.updateTickitByAdmin(tid, tickit),HttpStatus.CREATED);
	}
	
	@GetMapping("admin/allTickits")
	public ResponseEntity<List<Tickit>> getAllTickits(){
		return new ResponseEntity<List<Tickit>>(tickitService.getAllTickits(),HttpStatus.FOUND);
	}
	
	@GetMapping("employee/allTickits/{eid}")
	public ResponseEntity<List<Tickit>> getAllIssueByEmployee(@PathVariable("eid") Integer eid){
		return new ResponseEntity<List<Tickit>>(tickitService.getAllTickitIssueByEmployee(eid),HttpStatus.FOUND);
	}
	
	@DeleteMapping("admin/delete/{tid}")
	public ResponseEntity<String> deleteByTickitId(@PathVariable("tid") Integer tid){
		return new ResponseEntity<String>(tickitService.deleteTickitById(tid),HttpStatus.OK);
	}

}
