package hu.gyuuu.hrmanager.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class PermissionEvaulatorImpl implements PermissionEvaluator {

    @SuppressWarnings("rawtypes")
    private Map<String, Permission> permissions = new HashMap<>();
    private ApplicationContext         applicationContext;

    @Autowired
    public PermissionEvaulatorImpl(ApplicationContext applicationContext) {
        super();
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Permission<?> oPermission = permissions.get(permission);
        if (oPermission == null) {
            throw new UnsupportedOperationException("unknown permission");
        }
        return oPermission.hasPermission(authentication, targetDomainObject);

    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        throw new UnsupportedOperationException();
    }

    @PostConstruct
    public void lookupPermission() {
        permissions = applicationContext.getBeansOfType(Permission.class);
    }

}
