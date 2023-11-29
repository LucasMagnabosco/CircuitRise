 package ucs.CircuitRise.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="FINAL_TABLE")

public class FinalTable implements Serializable{

	
	private static final long serialVersionUID = 40L;

	@Id
	@Column(name="SEASON_YEAR")
	private int year;
	
	@ManyToMany (fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(name = "SEASON_PILOTS",
    		joinColumns =  @JoinColumn(name = "SEASON_YEAR"),
            inverseJoinColumns  = @JoinColumn(name = "pilot_id"))
	private Set<Pilot> pilots = new HashSet<Pilot>();
	
	@ManyToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL })
    @JoinTable(name = "SEASON_TEAMS",
    		joinColumns = @JoinColumn(name = "SEASON_YEAR"),
			inverseJoinColumns =  @JoinColumn(name = "team_id"))
	private Set<Team> teams = new HashSet<Team>();
	
	@Embedded
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<Stage> stages = new HashSet<>();
	
	public FinalTable(int year) {
		this.year = year;
	}
	public FinalTable() {
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void deleteClass() {
		pilots.removeAll(pilots);
		teams.removeAll(teams);
	}
	
	public List<Pilot> getPilots() {
		List<Pilot> p = new ArrayList<Pilot>(pilots);
		return p;
	}
	public void setPilots(Set<Pilot> pilots) {
		this.pilots = pilots;
	}
	
	public List<Team> getTeams() {
		List<Team> t = new ArrayList<Team>(teams);
		return t;
	}
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	
	public List<Stage> getStages() {
		List<Stage> s = new ArrayList<Stage>(stages);
		return s;
	}
	public void setStages(Set<Stage> stages) {
		this.stages = stages;
	}
	public void addStage(Stage stage) {
		stages.add(stage);
	}
	public void removeStage(Stage s) {
		stages.remove(s);
	}
	@Override
	public String toString() {
		return "Temporada " + Integer.toString(year);
	}
	
}
