package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	
	public boolean ajouterEntreprise(Entreprise entreprise);
	public boolean ajouterDepartement(Departement dep);
	public int affecterDepartementAEntreprise(int depId, int entrepriseId);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	public boolean deleteEntrepriseById(int entrepriseId);
	public boolean deleteDepartementById(int depId);
	public Entreprise getEntrepriseById(int entrepriseId);
}
