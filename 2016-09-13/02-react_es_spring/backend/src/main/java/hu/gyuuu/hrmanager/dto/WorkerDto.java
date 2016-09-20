package hu.gyuuu.hrmanager.dto;

import java.util.ArrayList;
import java.util.List;

import hu.gyuuu.hrmanager.dto.workerreservation.WorkerReservationDto;
import hu.gyuuu.hrmanager.enums.ReservationStatus;
import hu.gyuuu.hrmanager.persistence.bean.Worker;

public class WorkerDto {
    private Long                       id;
    private String                     name;
    private String                     biography;
    private List<WorkerReservationDto> reservations;

    public WorkerDto(Long id, String name, String biography, List<WorkerReservationDto> reservations) {
        super();
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<WorkerReservationDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<WorkerReservationDto> reservations) {
        this.reservations = reservations;
    }

    public static WorkerDto createFrom(Worker worker) {
        List<WorkerReservationDto> reservations = new ArrayList<>();
        worker.getReservations().stream().filter(
                reservation -> reservation.getStatus() == null || reservation.getStatus() == ReservationStatus.APPROVED)
                .forEach(reservation -> {
                    reservations.add(WorkerReservationDto.createFrom(reservation));
                });
        return new WorkerDto(worker.getId(), worker.getName(), worker.getBiography(), reservations);
    }

}
