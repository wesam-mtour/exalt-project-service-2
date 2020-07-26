package com.exalt.partssystem.service;

import com.exalt.partssystem.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAll(int page, int pageSize);

    Company get(String name);

    Company save(Company company);

    List<Company> getNearCompany(Double Longitude, Double Latitude, Double maxDistance);

    Company delete(String name);
}
