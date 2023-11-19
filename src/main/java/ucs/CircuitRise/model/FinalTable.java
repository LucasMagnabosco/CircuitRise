 package ucs.CircuitRise.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FINAL_TABLE")

public class FinalTable implements Serializable{

	
	private static final long serialVersionUID = 40L;

	@Id
	@Column(name="SEASON_YEAR")
	private int year;
	
	@Embedded
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<Pilot> pilots = new HashSet<Pilot>();
	@Embedded
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<Team> teams = new HashSet<Team>();
	
	@Embedded
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<Stage> stages = new HashSet<Stage>();
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Set<Pilot> getPilots() {
		return pilots;
	}
	public void setPilots(Set<Pilot> pilots) {
		this.pilots = pilots;
	}
	public Set<Team> getTeams() {
		return teams;
	}
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	public Set<Stage> getStages() {
		return stages;
	}
	public void setStages(Set<Stage> stages) {
		this.stages = stages;
	}
	
	public String toString() {
		return "Temporada " + Integer.toString(year);
	}
	
}
