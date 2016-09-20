package hu.gyuuu.hrmanager.dto.workerreservation;

import javax.validation.constraints.NotNull;

public class DecideWorkerReservation {

    public enum Decision {
        APPROVE, REJECT
    }

    @NotNull
    private Long     reservationId;
    @NotNull
    private Decision decision;
    
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

}
