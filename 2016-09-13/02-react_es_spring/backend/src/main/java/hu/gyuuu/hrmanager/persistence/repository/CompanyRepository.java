package hu.gyuuu.hrmanager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.gyuuu.hrmanager.persistence.bean.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

    public Company findByIdentifier(String identifier);
}
