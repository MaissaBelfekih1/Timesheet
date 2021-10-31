package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	private static final Logger logger = LogManager.getLogger(EmployeServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	
	@Override
	public int ajouterEntreprise(Entreprise entreprise) {
		//oui
		return 0;
	}
	@Override
	public int ajouterDepartement(Departement dep) {
		try{
		deptRepoistory.save(dep);
		logger.info(" departement ajouté)");
    	logger.debug("ajout departement)");
		}
	catch (Exception e) {
		 logger.error("erreur ajout departement");
	}
	
		return dep.getId();

	}
	@Override
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//oui
		
	}
	@Override
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		List<String> depNames = new ArrayList<>();
		try{
		Entreprise entrepriseManagedEntity = this.entrepriseRepoistory.findById(entrepriseId).get();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
			logger.info(" liste de tout les departements par entreprise affiché)");
	    	logger.debug("tout les departements par entreprise)");
			
		}
		}
		catch (Exception e) {
			 logger.error(" liste de tout les departements par entreprise failed");
		}
		
		return depNames;
	}
	@Override
	public void deleteEntrepriseById(int entrepriseId) {
		//oui
		
	}
	@Transactional
	public int deleteDepartementById(int depId) {
		try{
		this.deptRepoistory.delete(this.deptRepoistory.findById(depId).get());
		logger.info(" departement suprimé avec succés )");
    	logger.debug("suppression departement)");
		}
		catch (Exception e) {
			 logger.error(" suppression departement failed");
		}
		return 0;		
	}
	@Override
	public Entreprise getEntrepriseById(int entrepriseId) {
		//oui
		return null;
	}
	
	
}
