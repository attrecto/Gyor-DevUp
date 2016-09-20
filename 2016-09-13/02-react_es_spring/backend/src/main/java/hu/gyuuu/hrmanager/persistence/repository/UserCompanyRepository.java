package hu.gyuuu.hrmanager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.gyuuu.hrmanager.persistence.bean.UserCompany;
import hu.gyuuu.hrmanager.persistence.bean.id.UserCompanyId;

public interface UserCompanyRepository extends JpaRepository<UserCompany, UserCompanyId> {

}
