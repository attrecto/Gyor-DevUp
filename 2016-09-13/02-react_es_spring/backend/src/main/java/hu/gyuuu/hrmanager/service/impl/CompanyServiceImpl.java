package hu.gyuuu.hrmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.gyuuu.hrmanager.dto.CompanyDto;
import hu.gyuuu.hrmanager.dto.wrapper.CollectionWrapper;
import hu.gyuuu.hrmanager.persistence.bean.Company;
import hu.gyuuu.hrmanager.persistence.repository.CompanyRepository;
import hu.gyuuu.hrmanager.service.CompanyService;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepo;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepo) {
        super();
        this.companyRepo = companyRepo;
    }

    @Override
    @PreAuthorize("hasAuthority('VIEW_COMPANIES')")
    public CollectionWrapper<CompanyDto> findCompanies() {
        List<Company> companies = companyRepo.findAll();
        List<CompanyDto> result = new ArrayList<>();
        companies.forEach(company -> {
            result.add(CompanyDto.createFrom(company));
        });
        return new CollectionWrapper<>(result);
    }
}
