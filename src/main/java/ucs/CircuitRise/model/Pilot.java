package ucs.CircuitRise.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PILOT")


public class Pilot implements Serializable{
	
	
	private static final long serialVersionUID = 10L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="PILOT_NUM")
	private int number;
	@Column(name="PILOT_POINTS")
	private int points;
	@Column(name="PILOT_WINS")
	private int wins;
	@Column(name="PILOT_PODIUM")
	private int podiums;
	@Column(name="PILOT_NAME", length=30, nullable=false)
	private String name;
	
	
	
	@ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
		
	@Embedded
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private Collection<FinalTime> finalTime = new ArrayList<FinalTime>();
	@Embedded
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<FastestLap> fastestLap = new HashSet<FastestLap>();
	
	public Pilot(String name, int num) {
		this.name = name;
		this.number = num;
	}
	public Pilot() {
	}
	public void resetSeason() {
		this.points = 0;
		this.podiums = 0;
		this.wins = 0;
	}
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
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Collection<FinalTime> getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(Collection<FinalTime> finalTime) {
		this.finalTime = finalTime;
	}
	public Set<FastestLap> getFastestLap() {
		return fastestLap;
	}
	public void setFastestLap(Set<FastestLap> fastestLap) {
		this.fastestLap = fastestLap;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
