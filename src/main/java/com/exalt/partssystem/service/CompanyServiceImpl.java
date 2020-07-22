package com.exalt.partssystem.service;

import com.exalt.partssystem.model.Company;
import com.exalt.partssystem.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> get(Double Longitude, Double Latitude, Double maxDistance) {
        return companyRepository.nearby(Longitude,Latitude , maxDistance);
    }
}
