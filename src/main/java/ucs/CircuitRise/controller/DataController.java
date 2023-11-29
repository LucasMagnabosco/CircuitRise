package ucs.CircuitRise.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ucs.CircuitRise.exceptions.ExcecaoEquipeCheia;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;
import ucs.CircuitRise.model.FinalTable;
import ucs.CircuitRise.model.FinalTime;
import ucs.CircuitRise.model.Pilot;
import ucs.CircuitRise.model.Stage;
import ucs.CircuitRise.model.Team;


public class DataController {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dados");
	Utility util = new Utility();
	
	
	public List<FinalTime> teste() {
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Stage where stage_name = :value");
		q.setParameter("value", "Australia");
		Stage stg = (Stage) q.getSingleResult();
		List<FinalTime> list = stg.getFinalTime();
		return list;
	}
	
	public void setPoints(Stage stg){
		List<FinalTime> finalT = stg.getFinalTime();
		for(int i=0; i<finalT.size(); i++) {
			if(i<10 && finalT.get(i).isBestLap()) {
				finalT.get(i).sumPoints(1);
			}
		}
		if(finalT.size()>0) {
			finalT.get(0).sumPoints(25);
			finalT.get(0).getPilot().countPoints(25);
			finalT.get(0).getPilot().countWins();
			finalT.get(0).getPilot().countPodiums();
		}else if(finalT.size()>1) {
			finalT.get(1).sumPoints(18);
			finalT.get(1).getPilot().countPoints(18);
			finalT.get(1).getPilot().countPodiums();
		}else if(finalT.size()>2) {
			finalT.get(2).sumPoints(15);
			finalT.get(2).getPilot().countPoints(15);
			finalT.get(2).getPilot().countPodiums();
		}else if(finalT.size()>3) {
			finalT.get(3).sumPoints(12);
			finalT.get(3).getPilot().countPoints(12);
		}else if(finalT.size()>4) {
			finalT.get(4).sumPoints(10);
			finalT.get(4).getPilot().countPoints(10);
		}else if(finalT.size()>5) {
			finalT.get(5).sumPoints(8);
			finalT.get(5).getPilot().countPoints(8);
		}else if(finalT.size()>6) {
			finalT.get(6).sumPoints(6);
			finalT.get(6).getPilot().countPoints(6);
		}else if(finalT.size()>7) {
			finalT.get(7).sumPoints(4);
			finalT.get(7).getPilot().countPoints(4);
		}else if(finalT.size()>8) {
			finalT.get(8).sumPoints(2);
			finalT.get(8).getPilot().countPoints(2);
		}else if(finalT.size()>9) {
			finalT.get(9).sumPoints(1);
			finalT.get(9).getPilot().countPoints(1);
		}
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin(); 
		manager.merge(stg);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public void sumTeams(FinalTable year) {
		List<Team> teams = new ArrayList<>(year.getTeams());
		for(Team t : teams) {
			//t.teamUpdate();
		}
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin(); 
		manager.merge(year);
		manager.getTransaction().commit();
		manager.close();
	}
	
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
	
	
	public void registerTeam(String name) throws ExcecaoEspacoVazio, ExcecaoObjetoJaCadastrado, ExcecaoNotNumber {
		util.check(name);
		EntityManager manager = factory.createEntityManager();
		String frase = "select count(*) from Team where team_name = :value";
		util.duplicates(manager,  frase,  name);
		Team team = new Team(name);
		util.commit(manager, team);
	}
	
	public void registerStage(FinalTable ft, String name, String date, String time, String lapsS, String lengthS, String sequenceS) throws ExcecaoEspacoVazio, ExcecaoNotNumber, ExcecaoObjetoJaCadastrado {
		util.check(name, date, time);
		util.checkNum(lapsS, lengthS, sequenceS);
		int laps = Integer.parseInt(lapsS);
		int length = Integer.parseInt(lengthS);
		int sequence = Integer.parseInt(sequenceS);
		Stage stage = new Stage(name, date, time, laps, length, sequence);
		ft.addStage(stage);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();     
		manager.merge(ft);
		manager.getTransaction().commit();
		manager.close();
		
	}
		
	public void registerSeason(String yearS, Set<Pilot> pilots, Set<Team> teams) throws ExcecaoEspacoVazio, ExcecaoNotNumber, ExcecaoObjetoJaCadastrado {
		util.checkNum(yearS);
		int year = Integer.parseInt(yearS);
		String frase = "select count(*) from FinalTable where season_year = :value";
		EntityManager manager = factory.createEntityManager();
		util.duplicates(manager, frase, year);
		FinalTable season = new FinalTable(year);
		manager.getTransaction().begin();     
		manager.persist(season);
		season.setPilots(pilots);
		season.setTeams(teams);
		manager.merge(season);
		manager.getTransaction().commit();
		manager.close();
	}
	public FinalTime createFinalTime(Stage stg, Pilot p, String gridS) throws ExcecaoEspacoVazio, ExcecaoNotNumber {
		util.checkNum(gridS);
		int grid = Integer.parseInt(gridS);
		FinalTime ft = new FinalTime(stg, p, grid);
		return ft;
	}
	public void registerTimes(Stage stg, List<Pilot> pilots, List<FinalTime> list) {
		List<FinalTime> set = new ArrayList<>(list);
		stg.setFinalTime(set);
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();  
		manager.merge(stg);
		for(Pilot p : pilots) {
			manager.merge(p);			
		}
		manager.getTransaction().commit();
		manager.close();
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
	
	public void deleteStage(FinalTable ft, List<Stage> stgs) {
		EntityManager manager = factory.createEntityManager(); 	
		manager.getTransaction().begin();	
		for(Stage s : stgs) {
			Stage temp = manager.find(Stage.class, s.getId());
			ft.removeStage(s);
			manager.remove(temp);
		}
		manager.merge(ft);
		manager.getTransaction().commit();
		manager.close();
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
	public void deleteSeason(String yearS) throws ExcecaoEspacoVazio, ExcecaoNotNumber {
		util.checkNum(yearS);
		int year = Integer.parseInt(yearS);
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from FinalTable where season_year = :value");
		q.setParameter("value", year);
		FinalTable season = (FinalTable)q.getSingleResult();
		season.deleteClass();
		manager.getTransaction().begin();	
		manager.remove(season);
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
	public Object[][] stagesToArray(){
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Stage");
		List<Stage> stages = (List<Stage>) q.getResultList();
		Object [][] teamsArray = new Object[stages.size()][1];
		for(int i=0; i<stages.size();i++) {
			teamsArray[i][0] = stages.get(i).getName();
		}
		session.close();
		manager.close();
		return teamsArray;
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> getTeams(){
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Team");
		List<Team> teams = (List<Team>) q.getResultList();
		session.close();
		manager.close();
		return teams;
	}
	@SuppressWarnings("unchecked")
	public List<Stage> getStages(){
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Stage");
		List<Stage> stg = (List<Stage>) q.getResultList();
		session.close();
		manager.close();
		return stg;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pilot> getPilots() {
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Pilot");
		List<Pilot> pilots = (List<Pilot>) q.getResultList();
		session.close();
		manager.close();
		return pilots;
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
	
	@SuppressWarnings("unchecked")
	public List<FinalTable> getSeasons() {
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from FinalTable");
		List<FinalTable> seasonList = (List<FinalTable>) q.getResultList();
		session.close();
		manager.close();
		return seasonList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> getTeamsInfo(){
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery("from Team");
		List<Team> teams = (List<Team>) q.getResultList();
		session.close();
		manager.close();
		return teams;
	}
	@SuppressWarnings("unchecked")
	public Object[][] teamsInfoToArray(){
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
	
	public List<Stage> getStages(FinalTable ft) throws ExcecaoEspacoVazio{
		if(ft == null) {
			throw new ExcecaoEspacoVazio();
		}
		List<Stage> list = ft.getStages();
		return list;
	}
	public List<Pilot> getSeasonPilots(FinalTable ft) throws ExcecaoEspacoVazio{
		if(ft == null) {
			throw new ExcecaoEspacoVazio();
		}
		List<Pilot> list = ft.getPilots();
		return list;
	}
}
