 package ucs.CircuitRise.model;

import java.util.ArrayList;
import java.util.List;

public class Tabela {

	private int ano;
	private List<Piloto> pilotos = new ArrayList<Piloto>();
	private List<Equipe> equipes = new ArrayList<Equipe>();
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public List<Piloto> getPilotos() {
		return pilotos;
	}
	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}
	public List<Equipe> getEquipes() {
		return equipes;
	}
	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}
	
	
}
