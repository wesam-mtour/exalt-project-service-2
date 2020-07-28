package com.exalt.partssystem.tests;

import com.exalt.partssystem.model.Address;
import com.exalt.partssystem.model.Company;
import com.exalt.partssystem.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class CompanyControllerTest {

    @LocalServerPort
    private int localPort;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    private TestRestTemplate restTemplate;

    @PostConstruct
    public void initialize() {
        RestTemplateBuilder restTemplate = restTemplateBuilder.rootUri("http://localhost:" + localPort + "/api/v1/companies");
        this.restTemplate = new TestRestTemplate(restTemplate);
    }

    @AfterEach
    public void deleteAfterEach() {
        companyRepository.deleteAll();
    }

    @Test
    public void testGetAll() {
        Company company = createRandomCompany();
        ResponseEntity<Company> result = postCompany(company);
        /*
          Testing controller
         */
        List<Company> companies = restTemplate.exchange("/" +
                        "?page=1&pageSize=2",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Company>>() {
                }).getBody();
        /*
         *   Verify request succeed
         *   Companies List must be returned one company
         */
        assertEquals(companies.size(), 1);
    }

    @Test
    public void testCreateNewCompany() {
        Company company = createRandomCompany();
        ResponseEntity<Company> result = restTemplate.postForEntity("/", company, Company.class);
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
         Creating companies
         */
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(34.49432373, 31.48020882);
        Address address1 = new Address("Palestine", "Gaza", "Gaza st", geoJsonPoint);
        Company firstCompany = new Company("branch-1", address1);
        ResponseEntity<Company> result = postCompany(firstCompany);

        GeoJsonPoint geoJsonPoint1 = new GeoJsonPoint(34.49932373, 31.48020882);
        Address address2 = new Address("Jordan", "Amman", "Amman st", geoJsonPoint1);
        Company secondCompany = new Company("branch-2", address2);
        result = postCompany(secondCompany);
        /*
          Testing controller with values "changeable" --> Longitude=34.49432373&Latitude=31.48020882&maxDistance=1000.0 meter
         */
        List<Company> companies = restTemplate.exchange("/" +
                        "?Longitude=34.49432373&Latitude=31.48020882&maxDistance=1000.0",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Company>>() {
                }).getBody();
        /*
         *   Verify request succeed
         *   Companies List must be returned two Companies, they are company of GAZA and company of  Amman
         */
        assertEquals(companies.size(), 2);
    }

    @Test
    public void testDelete() throws URISyntaxException {
        Company company = createRandomCompany();
        ResponseEntity<Company> result = postCompany(company);
        final String baseUrl = "http://localhost:" + localPort + "/api/v1/companies?name=" + company.getName();
        URI uri = new URI(baseUrl);
        result = restTemplate.exchange(uri, HttpMethod.DELETE, null, Company.class);
        /*
         *   Verify request succeed
         */
        ResponseEntity<Company> result1 = restTemplate.getForEntity("/" + "?name=" + company.getName(), Company.class);
        assertNotNull(result1.getBody());
    }

    private Company createRandomCompany() {
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(34.49432373, 31.48020882);
        Address address = new Address("Palestine", "Gaza", "Gaza st", geoJsonPoint);
        Company company = new Company("branch-4", address);
        return company;
    }

    private ResponseEntity<Company> postCompany(Company company) {
        ResponseEntity<Company> result = restTemplate.postForEntity("/", company, Company.class);
        return result;
    }
}
