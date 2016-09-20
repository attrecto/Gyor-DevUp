package hu.gyuuu.hrmanager.security.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import hu.gyuuu.hrmanager.persistence.bean.Worker;
import hu.gyuuu.hrmanager.persistence.repository.WorkerRepository;
import hu.gyuuu.hrmanager.security.AuthenticationDetails;
import hu.gyuuu.hrmanager.security.enums.Authority;

@Component("manageWorker")
public class ManageWorker extends BasePermission<Long> {

    private WorkerRepository workerRepo;

    @Autowired
    public ManageWorker(WorkerRepository workerRepo) {
        super();
        this.workerRepo = workerRepo;
    }

    @Override
    public boolean hasPermissionInternal(Authentication authentication, Long workerId) {
        AuthenticationDetails authDetails = (AuthenticationDetails) authentication.getDetails();
        if (hasAuthority(authentication, Authority.MANAGE_ANY_WORKER)) {
            return true;
        }
        Worker worker = workerRepo.findOne(workerId);
        if(worker == null){
            return true;
        }
        if (hasAuthority(authentication, Authority.MANAGE_OWN_WORKER) && worker.getCompany().getId().equals(authDetails.getCompanyId())) {
            return true;
        }
        return false;
    }

}
