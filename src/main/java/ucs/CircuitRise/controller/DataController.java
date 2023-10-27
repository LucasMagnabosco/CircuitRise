package ucs.CircuitRise.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ucs.CircuitRise.exceptions.ExcecaoEquipeCheia;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;
import ucs.CircuitRise.model.Pilot;
import ucs.CircuitRise.model.Stage;
import ucs.CircuitRise.model.Team;


public class DataController {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dados");
	Utility util = new Utility();
	
	public void registerPilot(String name, String number) throws ExcecaoEspacoVazio, ExcecaoNotNumber, ExcecaoObjetoJaCadastrado {
		util.check(name);
		util.checkNum(number);
		int num = Integer.parseInt(number);
		EntityManager manager = factory.createEntityManager();
		String frase = "select count(*) from Pilot where pilot_name = :value";
		util.duplicates(manager, frase, name);
		Pilot pilot = new Pilot(name, num);
		util.commit(manager, pilot);
	}
	
	
	
	public void registerTeam(String name, String sid) throws ExcecaoEspacoVazio, ExcecaoObjetoJaCadastrado, ExcecaoNotNumber {
		util.check(name);
		util.checkNum(sid);
		int id = Integer.parseInt(sid);
		EntityManager manager = factory.createEntityManager();
		String frase = "select count(*) from Team where team_name = :value";
		util.duplicates(manager,  frase,  name);
		Team team = new Team(name, id);
		util.commit(manager, team);
	}
	
	public void registerStage() {
		
	}
	
	public void relatePilot(String teamName, String pilotName) throws ExcecaoEquipeCheia {
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Pilot where pilot_name = :value");
		q.setParameter("value", pilotName);
		Pilot pilot = (Pilot) q.getSingleResult();
		q = session.createQuery("from Team where team_name = :value");
		q.setParameter("value", teamName);
		Team team = (Team) q.getSingleResult();
		if(team.getPilots().size() >2) {
			throw new ExcecaoEquipeCheia();
		}
		team.addPilot(pilot);
		pilot.setTeam(team);
		manager.getTransaction().begin();	
		manager.merge(team);
		manager.merge(pilot);
		manager.getTransaction().commit();
		manager.close();
		session.close();
	}
	public void deleteRelation(String teamName, String pilotName) {
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Pilot where pilot_name = :value");
		q.setParameter("value", pilotName);
		Pilot pilot = (Pilot) q.getSingleResult();
		q = session.createQuery("from Team where team_name = :value");
		q.setParameter("value", teamName);
		Team team = (Team) q.getSingleResult();
		pilot.setTeam(null);
		team.removePilot(pilot);
		manager.getTransaction().begin();	
		manager.merge(team);
		manager.merge(pilot);
		manager.getTransaction().commit();
		manager.close();
		session.close();
	}
	
	public void deletePilot(String pilotName) {
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Pilot where pilot_name = :value");
		q.setParameter("value", pilotName);
		Pilot pilot = (Pilot) q.getSingleResult();
		manager.getTransaction().begin();	
		manager.remove(pilot);
		manager.getTransaction().commit();
		manager.close();
		session.close();
	}
	
	public void deleteStage(String stageName) {
		EntityManager manager = factory.createEntityManager(); 	
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from stage where stage_name = :value");
		q.setParameter("value", stageName);
		Stage stage = (Stage) q.getSingleResult();
		manager.getTransaction().begin();	
		manager.remove(stage);
		manager.getTransaction().commit();
		manager.close();
		session.close();
	}
	
	public void deleteTeam(String teamName) {
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Team where team_name = :value");
		q.setParameter("value", teamName);
		Team team = (Team) q.getSingleResult();
		manager.getTransaction().begin();	
		manager.remove(team);
		manager.getTransaction().commit();
		manager.close();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public Object[][] teamsToArray(){
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Team");
		List<Team> teams = (List<Team>) q.getResultList();
		Object [][] teamsArray = new Object[teams.size()][1];
		for(int i=0; i<teams.size();i++) {
			teamsArray[i][0] = teams.get(i).getName();
		}
		session.close();
		manager.close();
		return teamsArray;
	}
	@SuppressWarnings("unchecked")
	public Object[][] pilotsToArray() {
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Pilot");
		List<Pilot> pilots =(List<Pilot>) q.getResultList();
		Object[][] pilotsArray= new Object[pilots.size()][2];
		for(int i=0; i<pilots.size();i++) {
			pilotsArray[i][0] = pilots.get(i).getName();
			if(pilots.get(i).getTeam() != null) {
				pilotsArray[i][1] = pilots.get(i).getTeam().getName();
			}else {
				pilotsArray[i][1] = "";
			}
		}
		session.close();
		manager.close();
		return pilotsArray;
	}
	
}
