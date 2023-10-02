package ucs.CircuitRise.controller;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;

public class Utility {
	public void commit(EntityManager manager, Object obj) {
		manager.getTransaction().begin();     
		manager.persist(obj);
		manager.getTransaction().commit();
		manager.close();
	}
	public void duplicates(EntityManager manager, String frase, String value) throws ExcecaoObjetoJaCadastrado {
		Session session = manager.unwrap(Session.class);
		Query<?> q = session.createQuery(frase);
		q.setParameter("value", value);
		long count = (Long) q.getSingleResult();
		if(count>0) {
			manager.close();
			session.close();
			throw new ExcecaoObjetoJaCadastrado();
		}
	}
	public void check(String info) throws ExcecaoEspacoVazio {
		if(info.isEmpty()) {
			throw new ExcecaoEspacoVazio();
		}
	}
	public void checkNum(String info) throws ExcecaoEspacoVazio, ExcecaoNotNumber {
		if(!info.matches("[0-9]+")) {
			throw new ExcecaoNotNumber();
		}
		if(info.isEmpty()) {
			throw new ExcecaoEspacoVazio();
		}
	}
}
