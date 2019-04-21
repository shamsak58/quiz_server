package com.spring.restapi.repositories;

import com.spring.restapi.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationDALRepository extends MongoRepository<Location, String> {

}
