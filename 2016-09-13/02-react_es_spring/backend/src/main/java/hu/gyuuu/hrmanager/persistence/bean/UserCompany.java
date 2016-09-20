package hu.gyuuu.hrmanager.persistence.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.gyuuu.hrmanager.persistence.bean.id.UserCompanyId;
import hu.gyuuu.hrmanager.security.enums.UserCompanyRole;

@Entity
@Table(name = "user_companies")
@IdClass(UserCompanyId.class)
public class UserCompany {

    @Id
    @Column(name="user_id")
    private Long            userId;
    @Id
    @Column(name="company_id")
    private Long            companyId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private User            user;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", insertable = false, updatable = false, nullable = false)
    private Company         company;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserCompanyRole role;
    
    public UserCompany() {
    }

    public UserCompany(User user, Company company, UserCompanyRole role) {
        super();
        this.userId = user.getId();
        this.companyId = company.getId();
        this.user = user;
        this.company = company;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserCompanyRole getRole() {
        return role;
    }

    public void setRole(UserCompanyRole role) {
        this.role = role;
    }

}
