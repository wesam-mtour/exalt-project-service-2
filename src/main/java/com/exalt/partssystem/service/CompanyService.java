package com.exalt.partssystem.service;

import com.exalt.partssystem.model.Company;

import java.util.List;

public interface CompanyService {

    Company save (Company company);
    List<Company> get(Double Longitude , Double Latitude, Double maxDistance);
}
