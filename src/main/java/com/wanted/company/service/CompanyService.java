package com.wanted.company.service;

import com.wanted.company.domain.Company;
import com.wanted.company.repository.CompanyRepository;
import com.wanted.exception.CompanyExceptionType;
import com.wanted.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company validateExistCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CustomException(CompanyExceptionType.COMPANY_DOES_NOT_EXIST));
    }

}
