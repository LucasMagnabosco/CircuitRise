package ucs.CircuitRise.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;
import ucs.CircuitRise.model.Pilot;


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
	
	
	
}
