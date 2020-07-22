package com.exalt.partssystem.controller;


import com.exalt.partssystem.model.Company;
import com.exalt.partssystem.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private Logger logger = LoggerFactory.getLogger(CompanyController.class);

    /*
    To inject the object productService, implicitly
     */
    @Autowired
    private CompanyService companyService;


    @PostMapping(value = "/api/v1/companies")
    public Company createNewCompany(@RequestBody Company company) {
        logger.info("company controller method -createNewCompany");
        return companyService.save(company);
    }

    @GetMapping(value = "/api/v1/companies")
    public List<Company> getCompany(@RequestParam(name = "Longitude") Double Longitude, @RequestParam(name = "Latitude") Double Latitude,
                                   @RequestParam(name = "maxDistance") Double maxDistance){
        logger.info("company controller method -getCompany");
        return companyService.get(Longitude,Latitude,maxDistance);

    }

}
