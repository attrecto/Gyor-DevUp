package hu.gyuuu.hrmanager.security.permission;

import org.springframework.security.core.Authentication;

import hu.gyuuu.hrmanager.security.Permission;
import hu.gyuuu.hrmanager.security.enums.Authority;

public abstract class BasePermission<T> implements Permission<T> {
    
    protected boolean hasAuthority(Authentication authentication, Authority authority) {
        return authentication.getAuthorities().stream().anyMatch(a -> {
            return authority.name().equals(a.getAuthority());
        });
    }
    
    @Override
    public boolean hasPermission(Authentication authentication, Object targetObject) {
        @SuppressWarnings("unchecked")
        T target = (T) targetObject;
        return hasPermissionInternal(authentication, target);            
    }
    
    public abstract boolean hasPermissionInternal(Authentication authentication, T targetObject);

}
