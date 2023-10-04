package com.wanted.company.dto;

import com.wanted.company.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyIdResponse {
    private Long postId;

    public static CompanyIdResponse of(Company company) {
        return new CompanyIdResponse(company.getId());
    }
}
