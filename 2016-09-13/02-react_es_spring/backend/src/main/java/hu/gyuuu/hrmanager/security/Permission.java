package hu.gyuuu.hrmanager.security;

import org.springframework.security.core.Authentication;

public interface Permission<T> {

    public boolean hasPermission(Authentication auth, Object targetObject);
}
