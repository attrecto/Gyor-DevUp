package hu.gyuuu.hrmanager.persistence.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long                   id;
    @Column(nullable = false)
    private String                 name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String                 biography;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id")
    private Company                company;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "worker")
    private Set<WorkerReservation> reservations;
    
    {
        reservations = new HashSet<>();
    }

    public Worker() {
    }

    public Worker(String name, String biography, Company company) {
        super();
        this.name = name;
        this.biography = biography;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<WorkerReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<WorkerReservation> reservations) {
        this.reservations = reservations;
    }

}
