package hu.gyuuu.hrmanager.security.permission;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import hu.gyuuu.hrmanager.security.AuthenticationDetails;
import hu.gyuuu.hrmanager.security.enums.Authority;

@Component("viewUser")
public class ViewUser extends BasePermission<Long> {

    @Override
    public boolean hasPermissionInternal(Authentication authentication, Long userId) {
        if(hasAuthority(authentication, Authority.VIEW_USERS)){
            return true;
        }
        AuthenticationDetails authDetails = (AuthenticationDetails) authentication.getDetails();
        if(hasAuthority(authentication, Authority.VIEW_SELF )|| authDetails.getUserId().equals(userId)){
            return true;
        }
        return false;
    }

}
