package ucs.CircuitRise.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TEAM")

public class Team implements Serializable{
	
	private static final long serialVersionUID = 20L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="TEAM_NAME", length=40, nullable=false)
	private String name;
	@Column(name="TEAM_POINTS")
	private int points;
	@Column(name="TEAM_WINS")
	private int wins;
	@Column(name="TEAM_PODIUMS")
	private int podiums;
	
	@Embedded
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    private List<Pilot> pilots;
	
	public Team() {
	}
	
	public Team(String name) {
		this.name = name;
	}
	
	public void teamUpdate() {
		if(pilots.size()>0) {
			Pilot pilot1 = pilots.get(0);
			this.points += pilot1.getPoints();
			this.wins += pilot1.getWins();
			this.podiums = pilot1.getPodiums();
		}
		if(pilots.size()>1) {
			Pilot pilot2 = pilots.get(1);
			this.points += pilot2.getPoints();
			this.wins += pilot2.getWins();
			this.podiums = pilot2.getPodiums();
		}
	}
	public void addPilot(Pilot p) {
		pilots.add(p);
	}
	public void removePilot(Pilot p) {
		pilots.remove(p);
	}
	
	public List<Pilot> getPilots() {
		return pilots;
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
	public Long getId() {
		return id;
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
	@Override
	public String toString() {
		return name;
	}
}
