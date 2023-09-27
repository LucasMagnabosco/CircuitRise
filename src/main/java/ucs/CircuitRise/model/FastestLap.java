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
@Table(name="FASTEST_LAP")

public class FastestLap implements Serializable{


	private static final long serialVersionUID = 50L;
	
	@Id
	@GeneratedValue
	@Column(name="FASTEST_LAP_ID")
	private int id;
	
	private Duration lap;

	public void setFinalTime(LocalTime lt) {
		lap = Duration.ofHours(lt.getHour())
                .plusMinutes(lt.getMinute())
                .plusSeconds(lt.getSecond())
                .plusNanos(lt.getNano());
	}

	public Duration getFinalTime() {
		return lap;
	}
	
}
