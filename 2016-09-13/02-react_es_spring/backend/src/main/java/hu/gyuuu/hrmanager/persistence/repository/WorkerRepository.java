package hu.gyuuu.hrmanager.persistence.repository;

import java.util.Collection;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import hu.gyuuu.hrmanager.persistence.bean.Company;
import hu.gyuuu.hrmanager.persistence.bean.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    public Collection<Worker> findByCompany(Company company);
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("from Worker w where w.id = :id")
    public Worker findAndLockOne(Long id);
}
