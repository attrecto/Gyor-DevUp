package hu.gyuuu.hrmanager.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.gyuuu.hrmanager.persistence.bean.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
    
    @Query("from User u where u.status is null")
    public List<User> findByPendingApproval(Sort sort);
}
