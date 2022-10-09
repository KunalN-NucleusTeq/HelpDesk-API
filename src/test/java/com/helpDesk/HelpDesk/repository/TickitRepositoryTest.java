package com.helpDesk.HelpDesk.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.helpDesk.HelpDesk.model.Tickit;
import com.helpDesk.HelpDesk.repo.TickitRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@DataJpaTest
public class TickitRepositoryTest {
	
		@Autowired
	    private TickitRepository tickitRepository;
	    private Tickit tickit;
	    
	    
	    @BeforeEach
	    public void setUp() {
	        tickit = new Tickit(1,"catagory","subcatagory","open","low","desc",new Date(),new Date(),1);
	    }
	    
	    
	    @Test
	    public void givenProductToAddShouldReturnAddedProduct(){
	    	tickitRepository.save(tickit);
	    	Tickit fetchedProduct = tickitRepository.findById(tickit.getTickitId()).get();
	         assertEquals(1, fetchedProduct.getTickitId());
	    }
	    
	    
	    @AfterEach
	    public void tearDown() {
	    	tickitRepository.deleteAll();
	    	tickit = null;
	    }
	    
	    
	
	

}
