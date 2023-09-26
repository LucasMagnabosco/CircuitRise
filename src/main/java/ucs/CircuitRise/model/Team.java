package ucs.CircuitRise.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TEAM")

public class Team implements Serializable{
	
	private static final long serialVersionUID = 20L;
	
	
	@Column(name="TEAM_NAME")
	private String name;
	@Id
	@Column(name="TEAM_ID")
	private int id;
	@Column(name="TEMA_POINTS")
	private int points;
	@Column(name="TEAM_WINS")
	private int wins;
	@Column(name="TEAM_POINTS")
	private int podiums;
	
	@Embedded
	private Pilot pilot1;
	@Embedded
	private Pilot pilot2;
	
	
	public void countPoints() {
		this.points = pilot1.getPoints() + pilot2.getPoints();
	}
	
	public void countWins() {
		this.wins = pilot1.getWins() + pilot2.getWins();
	}
	
	public void countsPodiums() {
		this.podiums = pilot1.getPodiums() + pilot2.getPodiums();
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Pilot getPiloto1() {
		return pilot1;
	}

	public void setPiloto1(Pilot piloto1) {
		this.pilot1 = piloto1;
	}

	public Pilot getPiloto2() {
		return pilot2;
	}

	public void setPiloto2(Pilot piloto2) {
		this.pilot2 = piloto2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pilot getPilot1() {
		return pilot1;
	}

	public void setPilot1(Pilot pilot1) {
		this.pilot1 = pilot1;
	}

	public Pilot getPilot2() {
		return pilot2;
	}

	public void setPilot2(Pilot pilot2) {
		this.pilot2 = pilot2;
	}

	
	
}
