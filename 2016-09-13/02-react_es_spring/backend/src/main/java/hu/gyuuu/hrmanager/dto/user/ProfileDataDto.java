package hu.gyuuu.hrmanager.dto.user;

import java.util.Collection;

import hu.gyuuu.hrmanager.dto.CompanyDto;
import hu.gyuuu.hrmanager.dto.UserDto;

public class ProfileDataDto {
    private UserDto                user;
    private Collection<CompanyDto> companies;

    public ProfileDataDto(UserDto user, Collection<CompanyDto> companies) {
        super();
        this.user = user;
        this.companies = companies;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Collection<CompanyDto> getCompanies() {
        return companies;
    }

    public void setCompanies(Collection<CompanyDto> companies) {
        this.companies = companies;
    }

}
