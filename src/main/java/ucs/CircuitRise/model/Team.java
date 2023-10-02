package ucs.CircuitRise.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name="TEAM_POINTS")
	private int points;
	@Column(name="TEAM_WINS")
	private int wins;
	@Column(name="TEAM_PODIUMS")
	private int podiums;
	
	
	@OneToMany(mappedBy = "team")
    private List<Pilot> pilots;
	
	
	public void teamUpdate() {
		Pilot pilot1 = pilots.get(0);
		Pilot pilot2 = pilots.get(1);
		this.points = pilot1.getPoints() + pilot2.getPoints();
		this.wins = pilot1.getWins() + pilot2.getWins();
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Pilot getPilot1() {
		return pilots.get(0);
	}
	public void setPilot1(Pilot pilot1) {
		pilots.set(0, pilot1);
	}
	public Pilot getPilot2() {
		return pilots.get(1);
	}
	public void setPilot2(Pilot pilot2) {
		pilots.set(1, pilot2);
	}
}
