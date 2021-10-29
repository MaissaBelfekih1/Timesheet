package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	private static final Logger logger = LogManager.getLogger(EmployeServiceImpl.class);
	
	public int ajouterEmploye(Employe employe) {
		try {
		
	        logger.info(" ajouterEmploye(Employe employe)");
	    	logger.debug("Debugging ajouterEmploye(Employe employe)");
	       
			employeRepository.save(employe);
			
		} catch (Exception e) {
			 logger.error("Oops! We have an Error. employe n'est pas ajouter"+e);
		}
		
		 logger.info(employe+"est ajouté avec succes !");
		 return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId)  {
		try {
			
	        logger.info("la methode mettreAjourEmailByEmployeId");
	    	logger.debug("Debugging mettreAjourEmailByEmployeId)");
	    	Optional <Employe> emp = employeRepository.findById(employeId);
	    	if(emp.isPresent())
	    	{
	    	Employe employe = emp.get();
			employe.setEmail(email);
			if (email.length() > 20 ) {
				logger.warn("email trops long") ;
			employeRepository.save(employe);
			}
			
		}
		}
			catch (Exception e) {
			 logger.error("Oops! We have an Error. "+e);
		}
		
		
		 logger.info(email+"est modifier avec succes!");
	
		
		

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Optional <Employe> emp = employeRepository.findById(employeId);
		Optional <Departement> dep = deptRepoistory.findById(depId);
    	if(emp.isPresent() && dep.isPresent())
    	{
    	Employe employeManagedEntity = emp.get();
		Departement depManagedEntity = dep.get();
		
		try {
			
	
		logger.info("affecterEmployeADepartement(int employeId, int depId)");
    	logger.debug("Debugging ffecterEmployeADepartement(int employeId, int depId)");
		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{
			logger.warn("getEmployes() n'est pas null") ;
			depManagedEntity.getEmployes().add(employeManagedEntity);

		}
		
		} catch (Exception e) {
			
			
			logger.error("Oops! We have an Error here . "+e);
		}

	}
	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Optional <Employe> emp = employeRepository.findById(employeId);
		Optional <Departement> dept = deptRepoistory.findById(depId);
    	if(emp.isPresent() && dept.isPresent())
    	{
    		
    	Employe employeManagedEntity = emp.get();
	
    	
    	{
		try {
			
			
			logger.info("desaffecterEmployeDuDepartement(int employeId, int depId)");
	    	logger.debug("Debugging desaffecterEmployeDuDepartement(int employeId, int depId)");
			Departement dep = dept.get();
	
	    	

			int employeNb = dep.getEmployes().size();
			for(int index = 0; index < employeNb; index++){
				if(dep.getEmployes().get(index).getId() == employeId){
					dep.getEmployes().remove(index);
					break;//a revoir
				}
				logger.warn("dep.getEmployes().get(index).getId() not matching"+ employeId) ;
			}
				

			
			} catch (Exception e) {
				logger.error("Oops! We have an Error. "+e);
			}
    	}
    	}
	}

	public int ajouterContrat(Contrat contrat) {
		try {
			
	        logger.info(" ajouterContrat(Contrat contrat))");
	    	logger.debug("Debugging ajouterContrat(Contrat contrat)");
	       
	    	contratRepoistory.save(contrat);
			
		} catch (Exception e) {
			 logger.error("Oops! We have an Error. Contart n'est pas ajouter"+e);
		}
		
		
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		try {
			Optional <Employe> emp = employeRepository.findById(employeId);
			Optional <Contrat> con = contratRepoistory.findById(contratId);
	    	if(emp.isPresent() && con.isPresent())
	    	{
	    	Employe employeManagedEntity = emp.get();
			Contrat contratManagedEntity = con.get();
			
			logger.info("affecterContratAEmploye(int contratId, int employeId)");
	    	logger.debug("Debugging affecterContratAEmploye(int contratId, int employeId)");
			
			
			

			contratManagedEntity.setEmploye(employeManagedEntity);
			contratRepoistory.save(contratManagedEntity);
			
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Oops! We have an Error. contrat n'est pas affecté"+e);
		}
		
		
	}

	public String getEmployePrenomById(int employeId) {
		logger.info("IN getEmployePrenomById()");
		Optional <Employe> emp = employeRepository.findById(employeId);
		if(emp.isPresent())
		{ 
			Employe employeManagedEntity = emp.get();
		
     
		try {
	   logger.debug("Debugging getEmployePrenomById(int employeId)");
    		
    		
    	return employeManagedEntity.getPrenom();
		}
    	
		catch (Exception e) {
        	 logger.error("Oops! We have an Error"+e);
		}
		}
		
		return null ;
	}

	public void deleteEmployeById(int employeId)
	{
		Optional <Employe> emp = employeRepository.findById(employeId);

		try {
			if(emp.isPresent())
	    	{
	    	Employe employe = emp.get();
			logger.info("Delete deleteEmployeById()");
	    
			
			//Desaffecter l'employe de tous les departements
			//c'est le bout master qui permet de mettre a jour
			//la table d'association
			logger.debug("Debugging deleteEmployeById(int employeId)");
			for(Departement dep : employe.getDepartements()){
				logger.info("Delete "+employe+" from department");
				dep.getEmployes().remove(employe);
			}

			employeRepository.delete(employe);
	    	}
		} catch (Exception e) {
			 logger.error("error, contart n'est pas supprime"+e);
		}
	
	}

	public void deleteContratById(int contratId) {
		Optional <Contrat> con = contratRepoistory.findById(contratId);
    	
    	
	
		try {
			if( con.isPresent())
	    	{
				Contrat contratManagedEntity = con.get();
			contratRepoistory.delete(contratManagedEntity);
		} 
		}catch (Exception e) {
			// TODO: handle exception
			 logger.error("error, contart n'est pas supprime"+e);
		}
		

	}

	public int getNombreEmployeJPQL() {
		logger.info("in getNombreEmployeJPQL()");
		logger.info("Nombre employee");
		return employeRepository.countemp();
	
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		logger.info("in getAllEmployeNamesJPQL() ");
		logger.info("Liste Employes by Name");
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		

		logger.info("in getAllEmployebyEntrprise ");
		logger.info("Liste Employes by Entreprise");
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		try {
			logger.info("mettreAjourEmailByEmployeIdJPQL(String email, int employeId)");
			employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("error, email n'est pas modifier"+e);
		}
		

	}
	public void deleteAllContratJPQL() {
		try {
			
			logger.info("In deleteallContartJPQL()");
	    	logger.debug("Debugging  deleteAllContratJPQL())");
			 employeRepository.deleteAllContratJPQL();
			
		} catch (Exception e) {
			logger.error("error",e);
		}
        
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		try {
			logger.info("In getSalaireByEmployeIdJPQL(int employeId)");
			logger.info("salire by employe");
			return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	
		
		} catch (Exception e) {
			logger.error("error",e);
		}
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}
				

	public Double getSalaireMoyenByDepartementId(int departementId) {
		try {
			logger.info("getSalaireMoyenByDepartementId(int departementId)");
			logger.info("salire by departement");
			return employeRepository.getSalaireMoyenByDepartementId(departementId);
	
		
		} catch (Exception e) {
			logger.error("error",e);
		}
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		logger.info("Liste Timesheet By mission");
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		     logger.info("Liste All Employs");
				return (List<Employe>) employeRepository.findAll();
	}

}