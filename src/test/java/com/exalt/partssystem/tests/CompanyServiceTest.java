package com.exalt.partssystem.tests;

import com.exalt.partssystem.model.Address;
import com.exalt.partssystem.model.Company;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class CompanyServiceTest {

    private Logger logger = LoggerFactory.getLogger(CompanyServiceTest.class);
    @LocalServerPort
    private int localPort;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateNewCompany() {
        Company company = createRandomCompany();

        ResponseEntity<Company> result = restTemplate.postForEntity("http://localhost:" + localPort + "/api/v1/companies", company, Company.class);
        /*
         *   Verify request succeed
         */
        assertAll(
                () -> assertEquals(result.getBody().getName(), company.getName()),
                () -> assertEquals(result.getBody().getAddress().getCity(), company.getAddress().getCity()),
                () -> assertEquals(result.getBody().getAddress().getCountry(), company.getAddress().getCountry()),
                () -> assertEquals(result.getBody().getAddress().getStreet(), company.getAddress().getStreet()),
                () -> assertEquals(result.getBody().getAddress().getPoint().getX(),
                        company.getAddress().getPoint().getX()),
                () -> assertEquals(result.getBody().getAddress().getPoint().getY(),
                        company.getAddress().getPoint().getY())
        );
    }

    @Test
    public void TestGetNearCompany() {
        /*
         Creating companies "randomly"
         */
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(34.49432373, 31.48020882);
        Address address1 = new Address("Palestine", "Gaza", "Gaza st", geoJsonPoint);
        Company firstCompany = new Company("branch-1", address1);
        ResponseEntity<Company> result = restTemplate.postForEntity("http://localhost:" + localPort + "/api/v1/companies", firstCompany, Company.class);

        GeoJsonPoint geoJsonPoint1 = new GeoJsonPoint(34.49932373, 31.48020882);
        Address address2 = new Address("Jordan", "Amman", "Amman st", geoJsonPoint1);
        Company secondCompany = new Company("branch-2", address2);
        result = restTemplate.postForEntity("http://localhost:" + localPort + "/api/v1/companies", secondCompany, Company.class);
        /*
          Testing url with values "changeable" --> Longitude=34.49432373&Latitude=31.48020882&maxDistance=1000.0
         */
        List<Company> companies = restTemplate.exchange("http://localhost:" + localPort + "/api/v1/companies/" +
                        "?Longitude=34.49432373&Latitude=31.48020882&maxDistance=1000.0",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Company>>() {
                }).getBody();
        /*
         *   Verify request succeed
         *   Companies List must be returned two Companies, are first company of GAZA and company of  Amman
         */
        assertEquals(companies.size(), 2);
    }
    public Company createRandomCompany() {
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(34.49432373, 31.48020882);
        Address address = new Address("Palestine", "Gaza", "Gaza st", geoJsonPoint);
        Company company = new Company("branch-4", address);
        return company;
    }
}
