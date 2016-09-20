package hu.gyuuu.hrmanager.service.impl;

import static hu.gyuuu.hrmanager.ErrorKeys.USER_NOT_EXISTS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.gyuuu.hrmanager.dto.CompanyDto;
import hu.gyuuu.hrmanager.dto.UserDto;
import hu.gyuuu.hrmanager.dto.user.ProfileDataDto;
import hu.gyuuu.hrmanager.exception.ValidationException;
import hu.gyuuu.hrmanager.persistence.bean.User;
import hu.gyuuu.hrmanager.persistence.repository.UserRepository;
import hu.gyuuu.hrmanager.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        super();
        this.userRepo = userRepo;
    }

    @Override
    @PreAuthorize("hasPermission(#userId, 'viewUser')")
    public ProfileDataDto getProfileData(Long userId) {
        User user = userRepo.findOne(userId);
        if (user == null) {
            throw new ValidationException(USER_NOT_EXISTS);
        }
        UserDto userDto = UserDto.createFrom(user);
        List<CompanyDto> companyDtos = new ArrayList<>();
        user.getUserCompanies().stream().map(userCompany -> {
            return userCompany.getCompany();
        }).forEach(company -> {
            CompanyDto companyDto = CompanyDto.createFrom(company);
            companyDtos.add(companyDto);
        });
        return new ProfileDataDto(userDto, companyDtos);
    }
}
