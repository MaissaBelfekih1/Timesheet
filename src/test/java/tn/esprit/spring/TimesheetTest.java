package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.TimesheetServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTest {
	
    @Autowired
    TimesheetServiceImpl time;
    Mission m = new Mission("deplacement","deplacement");
 
	
    Date date = new Date(1633824000000l);
	Date date2 = new Date(1633824000000l);
	
	@Test
	public void testajouterMission()
	{
		assertEquals(true, time.ajouterMission(m));
		
	}

	@Test
	public void testaffecterMissionADepartement()
	{

		assertEquals(33,time.affecterMissionADepartement(33, 1));
	}

	@Test
	public void testajouterTimesheet()
	{
		assertEquals(true,time.ajouterTimesheet(33, 1, date, date2));
	}


	@Test
	 public void methodone() throws ParseException
	{
		 Date dddd = new Date();
		 dddd.setTime(1633824000000l);
		 Date ffff = new Date();
		 ffff.setTime(1639094400000l);
		 assertEquals(time.validerTimesheet(1,2,dddd,ffff,1),1);
		 
	}
	 @Test
	 public void methodtwo()
	 {
		 List<Mission> missions = new ArrayList<>();
		 Mission m = new Mission("aaa","hello there");
		 Departement d = new Departement("small");
		 m.setDepartement(d);
		 d.setId(1);
		 m.setId(1);
		 missions.add(m);
		 assertEquals(time.findAllMissionByEmployeJPQL(2),missions);
	 }
	 @Test
	 public void methodthree()
	 {
		 List<Employe> employes = new ArrayList<>();
		 Employe e = new Employe("ahmed","rais","ahmedrais@gmail.com",false,Role.TECHNICIEN);
		 e.setId(2);
		 employes.add(e);
		 assertEquals(time.getAllEmployeByMission(1),employes);
	 }

}
