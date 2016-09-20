package hu.gyuuu.hrmanager.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import hu.gyuuu.hrmanager.enums.UserAapprovalStatus;
import hu.gyuuu.hrmanager.persistence.bean.Company;
import hu.gyuuu.hrmanager.persistence.bean.User;
import hu.gyuuu.hrmanager.persistence.bean.UserCompany;
import hu.gyuuu.hrmanager.persistence.bean.id.UserCompanyId;
import hu.gyuuu.hrmanager.persistence.repository.CompanyRepository;
import hu.gyuuu.hrmanager.persistence.repository.UserCompanyRepository;
import hu.gyuuu.hrmanager.persistence.repository.UserRepository;
import hu.gyuuu.hrmanager.security.enums.Authority;

@Component
public class HRManagerAuthenticationProvider implements AuthenticationProvider {
    private UserRepository        userRepo;
    private CompanyRepository     companyRepo;
    private UserCompanyRepository userCompanyRepo;
    private PasswordEncoder       passwordEncoder;

    @Autowired
    public HRManagerAuthenticationProvider(UserRepository userRepo, CompanyRepository companyRepo,
            UserCompanyRepository userCompanyRepo, PasswordEncoder passwordEncoder) {
        super();
        this.userRepo = userRepo;
        this.companyRepo = companyRepo;
        this.userCompanyRepo = userCompanyRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HRManagerAuthenticationToken token = (HRManagerAuthenticationToken) authentication;
        String username = (String) token.getPrincipal();
        String password = (String) token.getCredentials();
        
        User user = authenticateUser(username, password);
        Company company = loadCompany(token);
        UsernamePasswordAuthenticationToken result = createAutneticationToken(authentication, user, company);
        return result;
    }

    private UsernamePasswordAuthenticationToken createAutneticationToken(Authentication authentication, User user,
            Company company) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Authority authority : user.getRole().getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(authority.name()));
        }
        if(company != null){
            UserCompany userCompany = userCompanyRepo.findOne(new UserCompanyId(user.getId(), company.getId()));
            if(userCompany == null){
                throw new BadCredentialsException("user.canNotLoginToCompany");
            }
            for (Authority authority : userCompany.getRole().getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(authority.name()));
            }
        }
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), authentication.getCredentials(), authorities);
        result.setDetails(new AuthenticationDetails(user.getId(), user.getDisplayName(), company != null?company.getId():null));
        return result;
    }

    private Company loadCompany(HRManagerAuthenticationToken token) {
        Company company = null;
        if (StringUtils.isNotBlank(token.getCompanyIdentifier())) {
            company = companyRepo.findByIdentifier(token.getCompanyIdentifier());
            if(company ==  null){
                throw new BadCredentialsException("companyIdentifier.invalid");                
            }
        }
        return company;
    }

    private User authenticateUser(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new BadCredentialsException("insufficient credentials");
        }
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new BadCredentialsException("username.invalid");
        }
        if (user.getStatus() == null || user.getStatus() != UserAapprovalStatus.APPROVED) {
            throw new DisabledException("user.notApproved");
        }
        if (!passwordEncoder.matches( password, user.getPassword() )) {
            throw new BadCredentialsException("password.invalid");
        }
        return user;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return HRManagerAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
