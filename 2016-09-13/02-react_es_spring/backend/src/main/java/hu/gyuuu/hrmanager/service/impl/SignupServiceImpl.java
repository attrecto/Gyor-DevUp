package hu.gyuuu.hrmanager.service.impl;

import static hu.gyuuu.hrmanager.ErrorKeys.COMPANY_IDENTIFIER_ALREADY_USED;
import static hu.gyuuu.hrmanager.ErrorKeys.EMAIL_ALREADY_USED;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.gyuuu.hrmanager.dto.registration.CompanyDto;
import hu.gyuuu.hrmanager.dto.registration.RegistrationDto;
import hu.gyuuu.hrmanager.dto.registration.UserDto;
import hu.gyuuu.hrmanager.exception.ValidationException;
import hu.gyuuu.hrmanager.persistence.bean.Company;
import hu.gyuuu.hrmanager.persistence.bean.ContactPerson;
import hu.gyuuu.hrmanager.persistence.bean.User;
import hu.gyuuu.hrmanager.persistence.bean.UserCompany;
import hu.gyuuu.hrmanager.persistence.repository.CompanyRepository;
import hu.gyuuu.hrmanager.persistence.repository.UserRepository;
import hu.gyuuu.hrmanager.security.enums.UserCompanyRole;
import hu.gyuuu.hrmanager.security.enums.UserRole;
import hu.gyuuu.hrmanager.service.SignupService;

@Service
@Transactional(readOnly = true)
public class SignupServiceImpl implements SignupService
{
	private UserRepository		userRepo;
	private CompanyRepository	companyRepo;
	private PasswordEncoder		passwordEncoder;

	@Autowired
	public SignupServiceImpl( UserRepository userRepo, CompanyRepository companyRepo,
			PasswordEncoder passwordEncoder )
	{
		super();
		this.userRepo = userRepo;
		this.companyRepo = companyRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional(readOnly = false)
	public void register( RegistrationDto dto )
	{
		validateRegistration( dto );
		User user = saveUser( dto );
		Company company = saveCompany( dto );
		saveUserCompany( user, company );
	}

	private void saveUserCompany( User user, Company company )
	{
		UserCompany userCompany = new UserCompany( user, company, UserCompanyRole.ADMIN );
		Set<UserCompany> userCompanies = new HashSet<>();
		userCompanies.add( userCompany );
		user.setUserCompanies( userCompanies );
	}

	private Company saveCompany( RegistrationDto dto )
	{
		CompanyDto companyDto = dto.getCompany();
		ContactPerson contactPerson = new ContactPerson( companyDto.getContact().getName(), companyDto.getContact().getPhone(), companyDto.getContact().getEmail() );
		Company company = new Company( companyDto.getName(), companyDto.getIdentifier(), contactPerson );
		companyRepo.save( company );
		return company;
	}

	private User saveUser( RegistrationDto dto )
	{
		UserDto userDto = dto.getUser();
		String encryptedPassword = passwordEncoder.encode( userDto.getPassword() );
		User user = new User( userDto.getEmail(), encryptedPassword, userDto.getName(), null, UserRole.USER );
		userRepo.save( user );
		return user;
	}

	private void validateRegistration( RegistrationDto dto )
	{
		UserDto userDto = dto.getUser();
		Map<String, String> fieldErrors = new HashMap<>();
		User user = userRepo.findByEmail( userDto.getEmail() );
		if( user != null )
		{
			fieldErrors.put( "user.email", EMAIL_ALREADY_USED );
		}
		CompanyDto companyDto = dto.getCompany();
		Company company = companyRepo.findByIdentifier( companyDto.getIdentifier() );
		if( company != null )
		{
			fieldErrors.put( "company.identifier", COMPANY_IDENTIFIER_ALREADY_USED );
		}
		if( !fieldErrors.isEmpty() )
		{
			throw new ValidationException( fieldErrors );
		}
	}
}
