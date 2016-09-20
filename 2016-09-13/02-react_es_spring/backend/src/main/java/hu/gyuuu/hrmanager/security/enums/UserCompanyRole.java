package hu.gyuuu.hrmanager.security.enums;

public enum UserCompanyRole {
    ADMIN(Authority.MANAGE_OWN_WORKER, Authority.DECIDE_OWN_WORKER_RESERVATION);

    private final Authority[] authorities;

    private UserCompanyRole(Authority... authorities) {
        this.authorities = authorities;
    }

    public Authority[] getAuthorities() {
        return authorities;
    }

}
