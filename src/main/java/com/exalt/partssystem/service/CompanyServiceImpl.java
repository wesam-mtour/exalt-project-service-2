package com.exalt.partssystem.service;

import com.exalt.partssystem.error.NotFoundExceptions;
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
        return companyRepository.findByName(name);
    }

    @Override
    public Company save(Company company) {
        companyRepository.deleteByName("test1");
        if (company.getName().equals("test2"))
            throw new NotFoundExceptions("sdfsdf");
        return companyRepository.save(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getNearCompany(Double Longitude, Double Latitude, Double maxDistance) {
        return companyRepository.nearby(Longitude, Latitude, maxDistance);
    }

    @Override
    public Company delete(String name) {
        return companyRepository.deleteByName(name);
    }
}
