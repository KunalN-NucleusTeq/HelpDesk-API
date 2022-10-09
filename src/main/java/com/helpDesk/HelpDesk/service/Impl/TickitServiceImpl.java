package com.helpDesk.HelpDesk.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpDesk.HelpDesk.exception.UnauthorisedExpection;
import com.helpDesk.HelpDesk.model.Catagory;
import com.helpDesk.HelpDesk.model.Employee;
import com.helpDesk.HelpDesk.model.Tickit;
import com.helpDesk.HelpDesk.model.Tickit_Employee;
import com.helpDesk.HelpDesk.repo.EmployeeRepositary;
import com.helpDesk.HelpDesk.repo.TickitRepository;
import com.helpDesk.HelpDesk.repo.Tickit_EmployeeRepository;
import com.helpDesk.HelpDesk.service.TickitService;

@Service
public class TickitServiceImpl implements TickitService {
	
	@Autowired
	private TickitRepository tickitRepository;
	
	@Autowired
	private EmployeeRepositary employeeRepositary;
	
	@Autowired
	private Tickit_EmployeeRepository tickit_EmployeeRepository;

	//This Method to generate ticket by employee
	@Override
	public Tickit createTickit(Tickit tickit) throws ValidationException {
		Optional<Employee> employee = employeeRepositary.findById(tickit.getEmpId());
		if(!employee.isPresent()) {
			throw new ValidationException("Employee Not found");
		}
		List<Tickit> listOfTickit = tickitRepository.listOfTickitByEmployeeId(tickit.getEmpId());
		for(Tickit t : listOfTickit) {
			if(t.getCatagory().equals(tickit.getCatagory())) {
				throw new ValidationException("This Tickit already Created");
			}
		}
		tickit.setIsCreated(new Date());
		Tickit tickit2 = tickitRepository.save(tickit);
		Tickit_Employee tickit_Employee = new Tickit_Employee();
		tickit_Employee.setEmpId(tickit.getEmpId());
		tickit_Employee.setTickitId(tickit2.getTickitId());
		tickit_EmployeeRepository.save(tickit_Employee);
		return tickit2;
	}

	//This method when ticket is updated by admin
	@Override
	public Tickit updateTickitByAdmin(Integer tid, Tickit tickit) throws UnauthorisedExpection {
		Tickit fTickit = tickitRepository.findById(tid).get();
		Optional<Tickit_Employee> ticket_emp = tickit_EmployeeRepository.findBytickitId(tid);
		if(fTickit!=null) {
			fTickit.setStatus(tickit.getStatus());
			fTickit.setPriority(tickit.getPriority());
			fTickit.setIsResolved(tickit.getIsResolved());
			fTickit.setEmpId(ticket_emp.get().getEmpId());
			tickitRepository.save(fTickit);
		}else {
			throw new UnauthorisedExpection("Tickit not found");
		}
		return fTickit;
	}
	
	public Tickit findTickitById(Integer tid) throws UnauthorisedExpection {
		Tickit fTickit = tickitRepository.findById(tid).get();
		if(fTickit==null) {
			throw new UnauthorisedExpection("Tickit Not Found");
		}
		
		return fTickit;
	}
	
	
	//this method to fetch all tickits its for admin
	public List<Tickit> getAllTickits(){
		List<Tickit> allTickits = tickitRepository.findAll();
		List<Tickit> result = new ArrayList<>();
		for(Tickit t : allTickits) {
			Optional<Tickit_Employee> ticket_emp = tickit_EmployeeRepository.findBytickitId(t.getTickitId());
			t.setEmpId(ticket_emp.get().getEmpId());
			result.add(t);
		}
		return result;
	}

	@Override
	public List<Tickit> getAllTickitIssueByEmployee(Integer eid) {
		List<Tickit> listOfTickits = tickitRepository.listOfTickitByEmployeeId(eid);
		return listOfTickits;
	}
	
	public String deleteTickitById(Integer tid) {
			Integer deleteresult =	tickitRepository.deleteTickitByTickitId(tid);
		return (deleteresult==1)?"Delete Success":"Delete Failed";
	}
	
	public Catagory updateCatagory(Catagory catagory) {
		
		
		return null;
	}
	
}
