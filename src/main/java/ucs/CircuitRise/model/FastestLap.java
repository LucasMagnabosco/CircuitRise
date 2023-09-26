package ucs.CircuitRise.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

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
