package hu.gyuuu.hrmanager.persistence.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import hu.gyuuu.hrmanager.enums.UserAapprovalStatus;
import hu.gyuuu.hrmanager.security.enums.UserRole;

@Entity
@Table(name = "users", indexes = { @Index(name = "ux_useres_user_name", unique = true, columnList = "email") })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long                id;
    @Column(name = "email", nullable = false)
    private String              email;
    @Column(name = "password", nullable = false)
    private String              password;
    @Column(name = "display_name", nullable = false)
    private String              displayName;
    @Column(name = "status", nullable = true)
    @Enumerated(EnumType.STRING)
    private UserAapprovalStatus status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole            role;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserCompany>    userCompanies;

    public User() {
    }

    public User(String email, String password, String displayName, UserAapprovalStatus status, UserRole role) {
        super();
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.status = status;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UserAapprovalStatus getStatus() {
        return status;
    }

    public void setStatus(UserAapprovalStatus status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Set<UserCompany> getUserCompanies() {
        return userCompanies;
    }

    public void setUserCompanies(Set<UserCompany> userCompanies) {
        this.userCompanies = userCompanies;
    }

}
