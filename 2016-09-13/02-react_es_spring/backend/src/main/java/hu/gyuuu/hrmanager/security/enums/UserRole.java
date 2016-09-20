package hu.gyuuu.hrmanager.security.enums;

public enum UserRole {
    USER(Authority.VIEW_SELF, Authority.VIEW_COMPANIES, Authority.VIEW_WORKERS, Authority.RESERVE_WORKER), 
    ADMIN(Authority.APPROVE_USER, Authority.VIEW_USERS, Authority.VIEW_COMPANIES, Authority.VIEW_WORKERS, Authority.MANAGE_OWN_WORKER);

    private final Authority[] authorities;

    private UserRole(Authority... authorities) {
        this.authorities = authorities;
    }

    public Authority[] getAuthorities() {
        return authorities;
    }

}
