package ucs.CircuitRise.model;

import java.util.ArrayList;
import java.util.List;

public class Etapa {

	private String nome;
	private String data;
	private String hora;
	private int voltas;
	
	private List<Piloto> pilotos = new ArrayList<Piloto>();
	private List<Equipe> equipes = new ArrayList<Equipe>();
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getVoltas() {
		return voltas;
	}
	public void setVoltas(int voltas) {
		this.voltas = voltas;
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
