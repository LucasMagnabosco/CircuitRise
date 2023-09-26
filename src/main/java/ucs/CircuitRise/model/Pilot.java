package ucs.CircuitRise.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PILOT")


public class Pilot implements Serializable{
	
	
	private static final long serialVersionUID = 10L;
	
	@Id
	@Column(name="PILOT_NUM")
	private int number;
	@Column(name="PILOT_POINTS")
	private int points;
	@Column(name="PILOT_WINS")
	private int wins;
	@Column(name="PILOT_PODIUM")
	private int podiums;
	@Column(name="PILOT_NAME")
	private String name;
	
	
	public void countPoints(int pontos) {
		this.points += pontos;
	}
	public void countWins() {
		this.wins++;
	}
	public void countPodiums() {
		this.podiums++;
	}
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getPodiums() {
		return podiums;
	}
	public void setPodiums(int podiums) {
		this.podiums = podiums;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
