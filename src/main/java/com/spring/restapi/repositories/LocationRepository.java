/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.restapi.repositories;

import com.spring.restapi.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */


public interface LocationRepository extends CrudRepository<Location, String> {


    //@Override
   // default Location findOne(String id) {return null;};

    @Override
    void delete(Location deleted);

   // Location getLocationRange(Double lat, Double lng);

}
