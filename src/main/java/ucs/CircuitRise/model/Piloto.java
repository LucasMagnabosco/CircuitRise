package ucs.CircuitRise.model;

import java.io.Serializable;




public class Piloto implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private int numero;
	private int pontos;
	private int wins;
	private int podios;
	
	public void somaPontos(int pontos) {
		this.pontos += pontos;
	}
	public void somaWins() {
		this.wins++;
	}
	public void somaPodios() {
		this.podios++;
	}
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getPodios() {
		return podios;
	}
	public void setPodios(int podios) {
		this.podios = podios;
	}
	
	
}
