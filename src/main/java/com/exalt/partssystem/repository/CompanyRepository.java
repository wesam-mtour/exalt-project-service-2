package com.exalt.partssystem.repository;

import com.exalt.partssystem.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Company findByName(String name);

    Company deleteByName(String name);

    /**
     * @param Longitude
     * @param Latitude
     * @param maxDistance
     * @return The closest documents.
     */
    @Query("{\n" +
            "    \"address.point\": {\n" +
            "        $near: {\n" +
            "            $geometry:\n" +
            "                { type: \"Point\", coordinates: [?0,?1] }, $maxDistance:?2\n" +
            "        }\n" +
            "    }\n" +
            "}")
    List<Company> nearby(Double Longitude, Double Latitude, Double maxDistance);


}
