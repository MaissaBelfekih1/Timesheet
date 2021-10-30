package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.TimesheetServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTest {
	
    @Autowired
    TimesheetServiceImpl time;
    Mission m = new Mission("deplacement","deplacement");
 
	@SuppressWarnings("deprecation")
	Date date = new Date(2021,10,07);
	@SuppressWarnings("deprecation")
	Date date2 = new Date(2021,10,22);
	
	@Test
	public void testajouterMission()
	{
		assertEquals(42, time.ajouterMission(m));
		
	}

	@Test
	public void testaffecterMissionADepartement()
	{

		assertEquals(33,time.affecterMissionADepartement(33, 1));
	}

	@Test
	public void testajouterTimesheet()
	{
		assertEquals(false,time.ajouterTimesheet(33, 1, date, date2));
	}

}
