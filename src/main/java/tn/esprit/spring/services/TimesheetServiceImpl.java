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
		logger.info("Ajouter une mission");
		logger.debug("Debugging log");
		missionRepository.save(mission);
		return true;
		}
		catch(Exception ex)
		{
			logger.error("Erreur");
		}
		
		return false;
	}

	@Override
	public int affecterMissionADepartement(int missionId, int depId) {
		Mission mission = missionRepository.findById(missionId).get();
		logger.info("Id de mission " +mission.getId());
		
		Departement dep = deptRepoistory.findById(depId).get();
		logger.info("Id de departement"+dep.getId());
		
		try
		{
		logger.info("Affecter Mission A Departement");
		logger.debug("Debugging log");
		
		mission.setDepartement(dep);
		missionRepository.save(mission);
		}
		
		catch(Exception ex)
		{
			logger.error("Erreur");
		}
		return mission.getId();
		
	}

	@Override
	public boolean ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
TimesheetPK timesheetPK = new TimesheetPK();
		
		timesheetPK.setDateDebut(dateDebut);
		logger.info("Date debut"+ dateDebut);
		
		timesheetPK.setDateFin(dateFin);
		logger.info("Date fin"+ dateFin);
		
		timesheetPK.setIdEmploye(employeId);
		logger.info("Id employe"+ employeId);
		
		timesheetPK.setIdMission(missionId);
		logger.info("Id mission"+ missionId);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		
		try
		{
		logger.info("ajouterTimesheet");
		logger.debug("Debugging log");
		timesheetRepository.save(timesheet);
		return true;
	}
		
		catch(Exception ex)
		{
			logger.error("Erreur");
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
