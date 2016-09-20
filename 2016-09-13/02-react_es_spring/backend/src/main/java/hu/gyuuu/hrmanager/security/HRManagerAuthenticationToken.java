package hu.gyuuu.hrmanager.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class HRManagerAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 820179824855244949L;
    private final String      companyIdentifier;

    public HRManagerAuthenticationToken(Object principal, Object credentials, String companyIdentifier) {
        super(principal, credentials);
        this.companyIdentifier = companyIdentifier;
    }

    public String getCompanyIdentifier() {
        return companyIdentifier;
    }

}
