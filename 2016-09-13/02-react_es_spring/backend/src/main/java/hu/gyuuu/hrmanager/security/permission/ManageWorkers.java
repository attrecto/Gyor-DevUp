package hu.gyuuu.hrmanager.security.permission;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import hu.gyuuu.hrmanager.security.AuthenticationDetails;
import hu.gyuuu.hrmanager.security.enums.Authority;

@Component("manageWorkers")
public class ManageWorkers extends BasePermission<Long> {

    @Override
    public boolean hasPermissionInternal(Authentication authentication, Long companyId) {
        AuthenticationDetails authDetails = (AuthenticationDetails) authentication.getDetails();
        if (hasAuthority(authentication, Authority.MANAGE_ANY_WORKER)) {
            return true;
        }
        if (hasAuthority(authentication, Authority.MANAGE_OWN_WORKER)
                && companyId.equals(authDetails.getCompanyId())) {
            return true;
        }
        return false;
    }

}
