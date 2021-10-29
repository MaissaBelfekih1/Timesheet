package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTest {

    private static final Logger logger = LogManager.getLogger(TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTest.class);

	@Test
	public void test()
	{
		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
	    

	}
	
	
}
