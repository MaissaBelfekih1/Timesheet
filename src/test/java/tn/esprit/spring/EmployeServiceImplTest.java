package tn.esprit.spring;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	
	
	@Autowired
	EmployeServiceImpl es=new EmployeServiceImpl();
	
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	MissionRepository mr;
	@Autowired
	TimesheetRepository timesheetRepository;
	private Date dateDebut = new Date();
	Employe employe = new Employe("maissa", "belfekih", "maissa@esprita.tn",true ,Role.CHEF_DEPARTEMENT);
	Contrat contrat= new Contrat(dateDebut, "test", 7000);
	String sDate1="2021-10-13";    
	String sDate2="2021-10-22";  

	@Test
	public void ajouterEmployeTest()
	{    
		
		es.ajouterEmploye(employe);
		Assertions.assertThat(employe.getId()).isGreaterThan(0);
	}
	
	

	 /*  
	  
	  @Test
	  public void getEmployePrenomByIdTest()
	  {
		  
		  
		  String ch = es.getEmployePrenomById(1);
		  
			Assertions.assertThat(ch).isEqualTo("belfekih");

	  }
	  */

	

	

	
	
/*		
	@Test
	public void deleteAllContratJPQLTest() {
		es.deleteAllContratJPQL();
		Assertions.assertThat(contratRepoistory.findAll()).isNull();
	}
	*/

	

	
	/*
	@Test
	public void getTimesheetsByMissionAndDateTest()
	{
		Employe employe =employeRepository.findById(1).get();
		Mission mission =mr.findById(1).get();
		
	<Timesheet> ts1= es.getTimesheetsByMissionAndDate(employe, mission, date1,date2);
		Assertions.assertThat(ts1.size()).isGreaterThan(0);

	}
	/
	
	
	@Test
	public void affecterContratAEmployeTest()
	{
		es.affecterContratAEmploye(11, 1);
		Contrat d =employeRepository.findById(1).get().getContrat(); 

		Assertions.assertThat(d.getReference()).isEqualTo(11);
	}
	*/
	
	@Test
	public void affecterEmployeADepartementTest()
	{
		es.affecterEmployeADepartement(1, 1);
		List<Departement> d =employeRepository.findById(1).get().getDepartements(); 

		Assertions.assertThat(d.isEmpty()).isFalse();
	}
	

	
	
	
	    @Test
	    public void updateEmployeeTest(){

			es.mettreAjourEmailByEmployeId("papa@outlook.fr", 1);

			Employe employeeUpdated=employeRepository.findById(1).get();
	        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("papa@outlook.fr");

	  }
	    
	    
	 
	  
	  @Test
	  public void getEmployePrenomByIdTest()
	  {
		  
		  
		  String ch = es.getEmployePrenomById(1);
		  
			Assertions.assertThat(ch).isEqualTo("chaieb");

	  }
	  
	 @Test
	 public void deleteEmployeByIdtest()
	 {
		    es.deleteEmployeById(4);
	        Employe employee1 = null;

	        Optional<Employe> optionalEmployee = employeRepository.findById(4);

	        if(optionalEmployee.isPresent()){
	            employee1 = optionalEmployee.get();
	        }
	        
	        Assertions.assertThat(employee1).isNull();

	 }
	    @Test
	  public void ajouterContrattest()
	  {
	    	es.ajouterContrat(contrat);
			Assertions.assertThat(contrat.getReference()).isGreaterThan(0);
	  }
	  
	  
	  
	@Test
	public void deleteContratByIdTest()
	{
	    es.deleteContratById(10);
	    Contrat c = null;

        Optional<Contrat> optionaContrat = contratRepoistory.findById(10);

        if(optionaContrat.isPresent()){
            c = optionaContrat.get();
        }
        
        Assertions.assertThat(c).isNull();

		
	}
	
	
	@Test
	public void getNombreEmployeJPQLTest()
	{
	int s=	es.getNombreEmployeJPQL();
	Assertions.assertThat(s).isGreaterThan(0);

	}
	
	
	
	
	@Test
	public void getAllEmployeNamesJPQLTest()
	{
		List<String>  s=es.getAllEmployeNamesJPQL();
		Assertions.assertThat(s.size()).isGreaterThan(0);

	}
	
	
	@Test
	public void getAllEmployeByEntrepriseTest()
	{
		List<Employe>  s=es.getAllEmployeByEntreprise(entrepriseRepoistory.findById(1).get());
		Assertions.assertThat(s.size()).isGreaterThan(0);

	}
	
	@Test
	public void mettreAjourEmailByEmployeIdJPQLTest()
	{
		es.mettreAjourEmailByEmployeIdJPQL("papa2@outlook.fr", 1);

		Employe employeeUpdated=employeRepository.findById(1).get();
        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("papa2@outlook.fr");
	}
	
	
	@Test
	public void deleteAllContratJPQLTest() {
		es.deleteAllContratJPQL();
		Assertions.assertThat(contratRepoistory.findAll()).isNull();
	}
	
	
	@Test
	public void getSalaireByEmployeIdJPQLTest() {
		float i =es.getSalaireByEmployeIdJPQL(1);
		System.out.println(i);
		Assertions.assertThat(i).isGreaterThan(0);

	}

	
	
	@Test
	public void getSalaireMoyenByDepartementIdTest() {
		Double e= es.getSalaireMoyenByDepartementId(1);
		Assertions.assertThat(e).isGreaterThan(0);

	
	}
	
	
	@Test
	public void getAllEmployesTest() {
    List<Employe> e =es.getAllEmployes();
    Assertions.assertThat(e.size()).isGreaterThan(0);
       }

	
	
	
	
	/**
	@Test
	public void getTimesheetsByMissionAndDateTest()
	{
		Employe employe =employeRepository.findById(1).get();
		Mission mission =mr.findById(1).get();
		
	<Timesheet> ts1= es.getTimesheetsByMissionAndDate(employe, mission, date1,date2);
		Assertions.assertThat(ts1.size()).isGreaterThan(0);

	}
	
	*/
	
	
	/*
	public void desaffecterEmployeDuDepartementTest()
	{
		es.desaffecterEmployeDuDepartement(1, 1);
		List<Departement> d =employeRepository.findById(1).get().getDepartements(); 
		
		Assertions.assertThat(d.isEmpty()).isTrue();

	}

	
	@Test
	public void affecterContratAEmployeTest()
	{
		es.affecterContratAEmploye(11, 1);
		Contrat d =employeRepository.findById(1).get().getContrat(); 

		Assertions.assertThat(d.getReference()).isEqualTo(11);
	}
	*/
	
	
	
	
}
