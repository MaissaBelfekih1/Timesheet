package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {

	@Autowired
	IEntrepriseService se;
	
	Entreprise e1 = new Entreprise("mouna","mariem");
	Entreprise e2 = new Entreprise("mouna1","mariem2");
	Departement d1 = new Departement("info");
	Entreprise e = new Entreprise();
	
	@Test
	public void testgetAllDepartementsNamesByEntreprise(){
		ArrayList<String> d= new ArrayList<>();
		d= (ArrayList<String>) se.getAllDepartementsNamesByEntreprise(1);
		assertEquals(d,se.getAllDepartementsNamesByEntreprise(1));
	}
	@Test
	public void testdeleteDepartementById(){ 
		assertEquals(true, se.deleteDepartementById(1));
	}
	
	@Test
	public void testajouterDepartement()
	{
	assertEquals(true,se.ajouterDepartement(d1));
	}
	
	@Test
    public void testajouterEntreprise(){

        assertEquals(true,se.ajouterEntreprise(e2));

    }
	@Test
    public void testgetEntrepriseById()
    {
        assertEquals(2,se.getEntrepriseById(2).getId());


    }
	
	 @Test
	    public void testaffecterDepartementAEntreprise()
	    {
	        assertEquals(2 ,se.affecterDepartementAEntreprise(2, 2));
	    }
	 
	 @Test
	    public void testdeleteEntrepriseById(){
	        assertEquals(true,se.deleteEntrepriseById(1));
	    }

}
