package ucs.CircuitRise.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Embeddable
@Table(name="FASTEST_LAP")

public class FastestLap implements Serializable{


	private static final long serialVersionUID = 50L;
	
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
