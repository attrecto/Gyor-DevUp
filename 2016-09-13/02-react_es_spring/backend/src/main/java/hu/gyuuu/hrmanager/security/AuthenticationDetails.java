package hu.gyuuu.hrmanager.security;

import java.io.Serializable;

public class AuthenticationDetails implements Serializable {
    private static final long serialVersionUID = -6077038434188029660L;
    private final Long   userId;
    private final String name;
    private final Long   companyId;

    public AuthenticationDetails(Long userId, String name, Long companyId) {
        super();
        this.userId = userId;
        this.name = name;
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Long getCompanyId() {
        return companyId;
    }

}
