package hu.gyuuu.hrmanager.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.gyuuu.hrmanager.persistence.bean.Worker;
import hu.gyuuu.hrmanager.persistence.bean.WorkerReservation;

public interface WorkerReservationRepository extends JpaRepository<WorkerReservation, Long>{

    @Query("from WorkerReservation wr where wr.worker = :worker and (wr.status is null or wr.status = 'APPROVED') and (wr.startDate <= :toDate and wr.endDate >= :fromDate)")
    public List<WorkerReservation> findByIntersectingDateRange(Worker worker, Date fromDate, Date toDate);
}