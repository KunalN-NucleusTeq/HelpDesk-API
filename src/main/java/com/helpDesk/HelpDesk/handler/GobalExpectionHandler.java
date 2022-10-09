package com.helpDesk.HelpDesk.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.helpDesk.HelpDesk.exception.UnauthorisedExpection;
import com.helpDesk.HelpDesk.model.ErrorMessage;

@ControllerAdvice
public class GobalExpectionHandler {
	
	@ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<ErrorMessage> handleConflict(final ValidationException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getLocalizedMessage());
		ErrorMessage errorMessage = new ErrorMessage("Validation Expection", errors);
        return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
        
	}
	
	@ExceptionHandler(value = UnauthorisedExpection.class)
	public ResponseEntity<ErrorMessage> handleUnauthorisedExpection(final UnauthorisedExpection un){
		List<String> errors = new ArrayList<>();
		errors.add(un.getLocalizedMessage());
		ErrorMessage errorMessage = new ErrorMessage("Record Not Found", errors);
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.NOT_FOUND);
	}

}
