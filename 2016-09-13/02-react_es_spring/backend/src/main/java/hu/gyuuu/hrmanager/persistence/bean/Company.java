package hu.gyuuu.hrmanager.persistence.bean;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "companies", indexes = {
        @Index(name = "ux_companies_identifier", columnList = "identifier", unique = true) })
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long             id;
    @Column(name = "name", nullable = false)
    private String           name;
    @Column(name = "identifier", nullable = false)
    private String           identifier;
    @Embedded
    private ContactPerson    contact;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "company")
    private Set<UserCompany> userCompanies;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    @OrderBy("name")
    private List<Worker>     workers;

    public Company() {
    }

    public Company(String name, String identifier, ContactPerson contact) {
        super();
        this.name = name;
        this.identifier = identifier;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public ContactPerson getContact() {
        return contact;
    }

    public void setContact(ContactPerson contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserCompany> getUserCompanies() {
        return userCompanies;
    }

    public void setUserCompanies(Set<UserCompany> userCompanies) {
        this.userCompanies = userCompanies;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

}
