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
@Table(name="STAGE")

public class Stage implements Serializable{
	
	private static final long serialVersionUID = 30L;
	
	@Id
	@Column(name="STAGE_ID")
	private int id;
	@Column(name="STAGE_NAME", length=30, nullable=false)
	private String name;
	@Column(name="DATE", length=12, nullable=false)
	private String date;
	@Column(name="TIME", length=6, nullable=false)
	private String time;
	@Column(name="LAPS")
	private int laps;
	@Column(name="LENGTH")
	private int length;
	
	@Embedded
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<FinalTime> finalTime = new HashSet<FinalTime>();
	@Embedded
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<FastestLap> fastestLap = new HashSet<FastestLap>();
	
	
	public Stage(int id, String name, String date, String time, int laps, int length) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.time = time;
		this.laps = laps;
		this.length = length;
	}
	
	public Stage(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public Stage() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getLaps() {
		return laps;
	}
	public void setLaps(int laps) {
		this.laps = laps;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}	
}
