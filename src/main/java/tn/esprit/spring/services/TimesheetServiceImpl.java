package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {

	private static final Logger logger = LogManager.getLogger(TimesheetServiceImpl.class);
	
	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	@Override
public boolean ajouterMission(Mission mission) {
		
		try
		{
		logger.info("Ajouter une mission donnée");
		missionRepository.save(mission);
		logger.info("Mission ajoutée");
		return true;
		}
		catch(Exception ex)
		{
			logger.error("Erreur dans l'ajout du mission"+ex);
		}
		
		return false;
	}
    
	public int affecterMissionADepartement(int missionId, int depId) {
		
		logger.info("Affecter Mission A Departement");
		
		logger.debug("Récupérer mission selon id "+missionId);
		Mission mission = missionRepository.findById(missionId).get();
		logger.debug("mission récupérée");
		
		logger.debug("Récupérer departement selon id "+depId);
		Departement dep = deptRepoistory.findById(depId).get();
		logger.debug("departement récupéré");
		
		try
		{
		
		logger.debug("enregistrer le departement récupéré a la mission récupérée ");
		mission.setDepartement(dep);
		logger.debug("enregistrement valide");
		
		logger.debug("enregistrer les modifications de la mission récupérée");
		missionRepository.save(mission);
		logger.debug("enregistrement valide");
		
		logger.info("Mission affectée");
		}
		
		catch(Exception ex)
		{
			logger.error("Erreur dans l'affectation"+ex);
		}
		return mission.getId();
	}

	public boolean ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		logger.info("Ajouter timesheet");
		
		logger.debug("Nouveau instance du clé primaire timesheetpk");
		TimesheetPK timesheetPK = new TimesheetPK();
		logger.debug("Ajouter les paramétres dans l'instance");
		
		logger.debug("Ajouter la date debut dans le clé primaire composé timesheetpk");
		timesheetPK.setDateDebut(dateDebut);
		logger.debug("Date debut"+ dateDebut);
		
		logger.debug("Ajouter la date fin dans le clé primaire composé timesheetpk");
		timesheetPK.setDateFin(dateFin);
		logger.debug("Date fin"+ dateFin);
		
		logger.debug("Ajouter un employe dans le clé primaire composé timesheetpk");
		timesheetPK.setIdEmploye(employeId);
		logger.debug("Id employe"+ employeId);
		
		logger.debug("Ajouter une mission dans le clé primaire composé timesheetpk");
		timesheetPK.setIdMission(missionId);
		logger.debug("Id mission"+ missionId);
		
		logger.debug("Nouveau instance du timesheet");
		Timesheet timesheet = new Timesheet();
		logger.debug("Ajouter le clé primaire au timesheet");
		
		timesheet.setTimesheetPK(timesheetPK);
		logger.debug("Ajouter la validité de timesheet");
		timesheet.setValide(false); //par defaut non valide
		
		try
		{
		logger.debug("Enregistrement...");
		timesheetRepository.save(timesheet);
		logger.info("Timesheet ajouté");
	}
		
		catch(Exception ex)
		{
			logger.error("Erreur dans l'ajout de timesheet"+ex);
		}
		return false;
	}



		
	public int validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		System.out.println("In valider Timesheet");
		Employe validateur = employeRepository.findById(validateurId).get();
		Mission mission = missionRepository.findById(missionId).get();
		//verifier s'il est un chef de departement (interet des enum)
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			System.out.println("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return 0;
		}
		//verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			System.out.println("l'employe doit etre chef de departement de la mission en question");
			return 0;	
		}

		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		//Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		return 1;
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}	

}
