package hu.gyuuu.hrmanager.service;

import hu.gyuuu.hrmanager.dto.pendingregistrations.DecideUserApprovalDto;
import hu.gyuuu.hrmanager.dto.pendingregistrations.PendingRegistrationDto;
import hu.gyuuu.hrmanager.dto.wrapper.CollectionWrapper;

public interface PendingRegistrationService
{

	CollectionWrapper<PendingRegistrationDto> findPendingRegistrations();

	void decide( DecideUserApprovalDto dto );

}
