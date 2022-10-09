package com.helpDesk.HelpDesk.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.helpDesk.HelpDesk.exception.UnauthorisedExpection;
import com.helpDesk.HelpDesk.model.Tickit;

public interface TickitService {
	
	public Tickit createTickit(Tickit tickit) throws ValidationException;

	Tickit updateTickitByAdmin(Integer tid, Tickit tickit) throws UnauthorisedExpection;
	
	Tickit findTickitById(Integer tid) throws UnauthorisedExpection;
	
	List<Tickit> getAllTickits();
	
	List<Tickit> getAllTickitIssueByEmployee(Integer eid);
	
	String deleteTickitById(Integer tid);
	
	

}
