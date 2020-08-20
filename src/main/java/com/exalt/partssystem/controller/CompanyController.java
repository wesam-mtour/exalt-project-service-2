package com.exalt.partssystem.controller;

import com.exalt.partssystem.error.exceptions.BadRequestExceptions;
import com.exalt.partssystem.model.Company;
import com.exalt.partssystem.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class CompanyController {

    private Logger logger = LoggerFactory.getLogger(CompanyController.class);
    /*
    To inject the object companyService, implicitly
     */
    @Autowired
    private CompanyService companyService;


    @GetMapping(value = "/api/v1/companies", params = {"page", "pageSize"})
    public List<Company> getAllCompanies(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        logger.info("company controller method -getAllCompanies");
        if (page < 1) {
            throw new BadRequestExceptions("Invalid page number");
        }
        if (pageSize < 1) {
            throw new BadRequestExceptions("Invalid page size number");
        }
        return companyService.getAll(page, pageSize);
    }

    @GetMapping(value = "/api/v1/companies", params = {"name"})
    public Company getCompany(@RequestParam("name") String name) {
        logger.info("company controller method -getCompany");
        return companyService.get(name);
    }

    @GetMapping(value = "/api/v1/companies", params = {"Longitude", "Latitude", "maxDistance"})
    public List<Company> getNearestCompanies(@RequestParam(name = "Longitude") Double Longitude,
                                             @RequestParam(name = "Latitude") Double Latitude,
                                             @RequestParam(name = "maxDistance") Double maxDistance) {
        logger.info("company controller method -getNearestCompanies");
        return companyService.getNearCompany(Longitude, Latitude, maxDistance);
    }

    @PostMapping(value = "/api/v1/companies")
    public Company createNewCompany(@Valid @RequestBody Company company) {
        logger.info("company controller method -createNewCompany");
        return companyService.save(company);
    }

    @DeleteMapping(value = "/api/v1/companies")
    public Company deleteCompany(@RequestParam(name = "name") String name) {
        logger.info("Company controller method -deleteCompany");
        return companyService.delete(name);
    }

}
