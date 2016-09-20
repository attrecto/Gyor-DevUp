package hu.gyuuu.hrmanager.persistence.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.gyuuu.hrmanager.enums.ReservationStatus;

@Entity
@Table(name = "worker_reservations")
public class WorkerReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long              id;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker            worker;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_company_id")
    private Company           bookingCompany;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @Column(nullable = false, name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date              startDate;
    @Column(nullable = false, name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date              endDate;

    public WorkerReservation() {
    }

    public WorkerReservation(Worker worker, Company bookingCompany, ReservationStatus status, Date startDate,
            Date endDate) {
        super();
        this.worker = worker;
        this.bookingCompany = bookingCompany;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Company getBookingCompany() {
        return bookingCompany;
    }

    public void setBookingCompany(Company bookingCompany) {
        this.bookingCompany = bookingCompany;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
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

}
