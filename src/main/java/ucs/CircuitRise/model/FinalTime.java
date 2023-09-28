package ucs.CircuitRise.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Embeddable
@Table(name="FINAL_TIME")

public class FinalTime implements Serializable{

	private static final long serialVersionUID = 60L;
	
	@Id
	@GeneratedValue
	@Column(name="FINAL_TIME_ID")
	private int id;
	
	@Column(name="RESULTS")
	private Duration finalTime;

	public void setFinalTime(LocalTime lt) {
		finalTime = Duration.ofHours(lt.getHour())
                .plusMinutes(lt.getMinute())
                .plusSeconds(lt.getSecond())
                .plusNanos(lt.getNano());
	}

	public Duration getFinalTime() {
		return finalTime;
	}
	
}
