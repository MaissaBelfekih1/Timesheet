package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

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
		//oui
		return 0;
	}
	@Override
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//oui
		
	}
	@Override
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		//oui
		return null;
	}
	@Override
	public void deleteEntrepriseById(int entrepriseId) {
		//oui
		
	}
	@Override
	public void deleteDepartementById(int depId) {
		//oui
		
	}
	@Override
	public Entreprise getEntrepriseById(int entrepriseId) {
		//oui
		return null;
	}
	
	
}
