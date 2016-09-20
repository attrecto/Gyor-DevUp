package hu.gyuuu.hrmanager.dto.workerreservation;

import java.util.Date;

import hu.gyuuu.hrmanager.dto.CompanyDto;
import hu.gyuuu.hrmanager.enums.ReservationStatus;
import hu.gyuuu.hrmanager.persistence.bean.WorkerReservation;

public class WorkerReservationDto {

    private Long              id;
    private Date              startDate;
    private Date              endDate;
    private CompanyDto        bookingCompany;
    private ReservationStatus status;

    public WorkerReservationDto(Long id, Date startDate, Date endDate, CompanyDto bookingCompany,
            ReservationStatus status) {
        super();
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingCompany = bookingCompany;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CompanyDto getBookingCompany() {
        return bookingCompany;
    }

    public void setBookingCompany(CompanyDto bookingCompany) {
        this.bookingCompany = bookingCompany;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public static WorkerReservationDto createFrom(WorkerReservation workerReservation) {
        if (workerReservation == null) {
            return null;
        }
        CompanyDto bookingCompanyDto = CompanyDto.createFrom(workerReservation.getBookingCompany());
        return new WorkerReservationDto(workerReservation.getId(), workerReservation.getStartDate(),
                workerReservation.getEndDate(), bookingCompanyDto, workerReservation.getStatus());
    }

}
