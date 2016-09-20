package hu.gyuuu.hrmanager.service;

import hu.gyuuu.hrmanager.dto.CompanyDto;
import hu.gyuuu.hrmanager.dto.wrapper.CollectionWrapper;

public interface CompanyService {

    CollectionWrapper<CompanyDto> findCompanies();

}
