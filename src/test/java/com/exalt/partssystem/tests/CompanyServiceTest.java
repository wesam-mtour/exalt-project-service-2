package com.exalt.partssystem.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ActiveProfiles("dev")
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Autowired
    private MongoTemplate mongoTemplate;
//    @Autowired
//    private CompanyRepository companyRepository;

    @Test
    public void test() {
        //companyRepository.nearby(35.1948899,31.8991839,1500.0);

    }


}
