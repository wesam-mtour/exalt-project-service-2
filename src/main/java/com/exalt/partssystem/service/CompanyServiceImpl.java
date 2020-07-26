package com.exalt.partssystem.service;

import com.exalt.partssystem.error.exceptions.ConflictExceptions;
import com.exalt.partssystem.error.exceptions.NotFoundExceptions;
import com.exalt.partssystem.model.Company;
import com.exalt.partssystem.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    /*
    To inject the object companyRepository, implicitly
     */
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Company> getAll(int page, int pageSize) {
        Pageable paging = PageRequest.of((page - 1) * pageSize, pageSize);
        Page<Company> pagedResult = companyRepository.findAll(paging);
        return pagedResult.getContent();
    }

    @Override
    @Transactional
    public Company get(String name) {
        Company company = companyRepository.findByName(name);
        if (company != null) {
            return company;
        } else {
            throw new NotFoundExceptions("Company not found");
        }
    }

    @Override
    public Company save(Company newCompany) {
        Company company = companyRepository.deleteByName(newCompany.getName());
        if (company == null) {
            return companyRepository.save(newCompany);
        } else {
            throw new ConflictExceptions(String.format("There is company with the same name ( %s ) ", company.getName()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getNearCompany(Double Longitude, Double Latitude, Double maxDistance) {
        return companyRepository.nearby(Longitude, Latitude, maxDistance);
    }

    @Override
    public Company delete(String name) {
        Company company = companyRepository.findByName(name);
        if (company != null) {
            return companyRepository.deleteByName(name);
        } else {
            throw new NotFoundExceptions("Not found company to deleting ");
        }
    }
}
