package ucs.CircuitRise.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="FINAL_TIME")

public class FinalTime implements Serializable, Comparable<FinalTime>{

	private static final long serialVersionUID = 60L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Type(type = "java.time.LocalTime")
    private LocalTime totalTime;
	@Column(name="MILI")
	private int mili;
	@Column(name="GRID")
	private int grid;
	@Column(name="POINTS")
	private int points;
	@Column(name="BEST")
	private boolean bestLap = false;
	
	@ManyToOne
    @JoinColumn(name = "Stage_id")
    private Stage stage;
	@ManyToOne
    @JoinColumn(name = "Pilot_id")
	private Pilot pilot;
	
	public FinalTime(Stage stg, Pilot p, int grid) {
		this.stage = stg;
		this.pilot = p;
		this.grid = grid;
	}
	public FinalTime() {
	}
	public void setTime(int horas, int minutos, int segundos, int mili) {
		this.totalTime = LocalTime.of(horas, minutos, segundos);
		this.mili = mili;
	}
	public int getMili() {
		return mili;
	}
	public LocalTime getFinalTime() {
		return totalTime;
	}
	public int getGrid() {
		return grid;
	}
	public void setGrid(int grid) {
		this.grid = grid;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public void sumPoints(int points) {
		this.points = points;
	}
	public boolean isBestLap() {
		return bestLap;
	}
	public void setBestLap() {
		bestLap = true;
	}
	public Pilot getPilot() {
		return pilot;
	}
	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}
	public void setMili(int mili) {
		this.mili = mili;
	}
	@Override
    public int compareTo(FinalTime ot) {
        int comparacaoLocalTime = this.totalTime.compareTo(ot.getFinalTime());
        if (comparacaoLocalTime == 0) {
            return Integer.compare(this.mili, ot.getMili());
        } else {
            return comparacaoLocalTime;
        }
    }
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String tempoFormatado = totalTime.format(formatter);

        return tempoFormatado + "." + String.format("%03d", mili);
	}
}
