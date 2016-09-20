package hu.gyuuu.hrmanager.service.impl;

import static hu.gyuuu.hrmanager.ErrorKeys.USER_APPROVAL_ALREADY_DECIDED;
import static hu.gyuuu.hrmanager.ErrorKeys.USER_NOT_EXISTS;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import hu.gyuuu.hrmanager.dto.CompanyDto;
import hu.gyuuu.hrmanager.dto.UserDto;
import hu.gyuuu.hrmanager.dto.pendingregistrations.DecideUserApprovalDto;
import hu.gyuuu.hrmanager.dto.pendingregistrations.PendingRegistrationDto;
import hu.gyuuu.hrmanager.dto.pendingregistrations.DecideUserApprovalDto.Decision;
import hu.gyuuu.hrmanager.dto.wrapper.CollectionWrapper;
import hu.gyuuu.hrmanager.enums.UserAapprovalStatus;
import hu.gyuuu.hrmanager.exception.ValidationException;
import hu.gyuuu.hrmanager.persistence.bean.Company;
import hu.gyuuu.hrmanager.persistence.bean.User;
import hu.gyuuu.hrmanager.persistence.repository.UserRepository;
import hu.gyuuu.hrmanager.service.PendingRegistrationService;

@Service
@Transactional(readOnly = true)
public class PendingRegistrationServiceImpl implements PendingRegistrationService
{
	private static final Log	LOG	= LogFactory.getLog( PendingRegistrationServiceImpl.class );
	private UserRepository		userRepo;

	@Autowired
	public PendingRegistrationServiceImpl( UserRepository userRepo )
	{
		super();
		this.userRepo = userRepo;
	}

	@Override
	@PreAuthorize("hasAuthority('APPROVE_USER')")
	public CollectionWrapper<PendingRegistrationDto> findPendingRegistrations()
	{
		List<PendingRegistrationDto> result = new ArrayList<>();
		Sort sort = new Sort( new Order( "displayName" ) );
		List<User> pendingUsers = userRepo.findByPendingApproval( sort );
		for( User user : pendingUsers )
		{
			UserDto userDto = UserDto.createFrom( user );
			if( user.getUserCompanies().size() != 1 )
			{
				LOG.debug( "Pending user " + user.getEmail() + " with invalid company count skipped!" );
				continue;
			}
			Company company = user.getUserCompanies().iterator().next().getCompany();
			CompanyDto companyDto = CompanyDto.createFrom( company );
			result.add( new PendingRegistrationDto( userDto, companyDto ) );
		}
		return new CollectionWrapper<>( result );
	}
	
	@Override
	@Transactional(readOnly=false)
	@PreAuthorize("hasAuthority('APPROVE_USER')")
	public void decide(DecideUserApprovalDto dto){
		User user = validateUserForDecision( dto );
		setApproval( dto, user );
	}

	private void setApproval( DecideUserApprovalDto dto, User user )
	{
		UserAapprovalStatus status = dto.getDecision() == Decision.APPROVE?UserAapprovalStatus.APPROVED:UserAapprovalStatus.REJECTED;
		user.setStatus(status);
	}

	private User validateUserForDecision( DecideUserApprovalDto dto )
	{
		User user = userRepo.findOne( dto.getUserId() );
		List<String> globalErrors = new ArrayList<>();
		if(user == null){
			globalErrors.add( USER_NOT_EXISTS );
		} else if(user.getStatus() != null){
			globalErrors.add( USER_APPROVAL_ALREADY_DECIDED );
		}
		if(!globalErrors.isEmpty()){
			String[] aGlobalErrors = new String[globalErrors.size()];
			globalErrors.toArray( aGlobalErrors );
			CollectionUtils.arrayToList( globalErrors );
			throw new ValidationException( aGlobalErrors );
		}
		return user;
	}
}
