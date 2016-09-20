package hu.gyuuu.hrmanager.persistence.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactPerson {

    @Column(name = "contact_name", nullable = false)
    private String name;
    @Column(name = "contact_phone")
    private String phone;
    @Column(name = "contact_email", nullable = false)
    private String email;

    public ContactPerson() {
    }

    public ContactPerson(String name, String phone, String email) {
        super();
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
