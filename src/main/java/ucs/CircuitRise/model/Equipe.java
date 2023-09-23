package ucs.CircuitRise.model;

public class Equipe {

	private String nome;
	private int id;
	private int pontos;
	private int wins;
	private int podios;
	
	private Piloto piloto1;
	private Piloto piloto2;
	
	public void somaPontos() {
		this.pontos = piloto1.getPontos() + piloto2.getPontos();
	}
	
	public void somaWin() {
		this.wins = piloto1.getWins() + piloto2.getWins();
	}
	
	public void somaPodio() {
		this.podios = piloto1.getPodios() + piloto2.getPodios();
	}

	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Piloto getPiloto1() {
		return piloto1;
	}

	public void setPiloto1(Piloto piloto1) {
		this.piloto1 = piloto1;
	}

	public Piloto getPiloto2() {
		return piloto2;
	}

	public void setPiloto2(Piloto piloto2) {
		this.piloto2 = piloto2;
	}	
}
