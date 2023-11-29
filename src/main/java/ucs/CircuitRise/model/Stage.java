package ucs.CircuitRise.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="STAGE")

public class Stage implements Serializable, Comparable<Stage>{
	
	private static final long serialVersionUID = 30L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	@Column(name="SEQUENCE")
	private int sequence;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<FinalTime> finalTime = new ArrayList<FinalTime>();
	
	
	public Stage(String name, String date, String time, int laps, int length, int sequence) {
		this.name = name;
		this.date = date;
		this.time = time;
		this.laps = laps;
		this.length = length;
		this.sequence = sequence;
	}

	public Stage() {
		
	}
	
	public Long getId() {
		return id;
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

	public int getSequence() {
		return sequence;
	}
	

	public List<FinalTime> getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(List<FinalTime> finalTime) {
		this.finalTime = finalTime;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return name;
	}
	@Override
	@Transient
    public int compareTo(Stage outroStage) {
        return Integer.compare(this.sequence, outroStage.getSequence());
    }
}
