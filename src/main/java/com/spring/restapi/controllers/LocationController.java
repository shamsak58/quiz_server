/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.restapi.controllers;

import com.spring.restapi.dal.LocationDAL;
import com.spring.restapi.models.Location;
import com.spring.restapi.models.RadiusMiles;
import com.spring.restapi.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
public class LocationController {
    
    @Autowired
    LocationRepository locationRepository;
    private final LocationDAL locationDAL;


    public LocationController(LocationRepository locationRepository, LocationDAL locationDAL) {
       // this.locationRepository = locationRepository;
        this.locationDAL = locationDAL;
    }

    @RequestMapping(method=RequestMethod.GET, value="/location/test")
    public String get() {
        System.out.println("location Server PING Test ");
        String ipadd = null;
        long count= 0;
        try {
            count = locationRepository.count();
            ipadd = java.net.InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
           return "Test Failed \n"+e.getMessage();
        }
        return "Server is Alive ....."+ipadd+"  MongoDB is Alive location count = "+count+" \n";
    }

    @RequestMapping(method=RequestMethod.GET, value="/location/getAll")
    public Iterable<Location> show() {
        System.out.println("location Server Test1 get all ");
        return locationRepository.findAll();
    } 
    
    @RequestMapping(method=RequestMethod.POST, value="/location")
    public String creatLocation(@RequestBody Location location) {
        System.out.println("location Server Insert save ");
        locationRepository.save(location);

        return location.getId();
    }

    @RequestMapping(method=RequestMethod.GET, value="/locationRange/{lat}/{lng}")
    //public Iterable<Location> show(@PathVariable Double lat, Double lng) {
    public List<RadiusMiles> show(@PathVariable("lat") Double lat, @PathVariable("lng") Double lng) {
        System.out.println(" where to "+locationDAL+"   resp "+locationRepository);
        System.out.println("location Server TestRange get lat: "+lat+" lng: "+lng);
        return locationDAL.getLocationRange(lat, lng);
    }


    @RequestMapping(method=RequestMethod.GET, value="/location/{id}")
    public Location show(@PathVariable String id) {
        System.out.println("location Server GetONE id "+id);
        return locationRepository.findOne(id);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/locationUpdate/{id}")
    public Location update(@PathVariable String id, @RequestBody Location location) {
        System.out.println("location Server UPDATE by id "+id);
        Location loc = locationRepository.findOne(id);
        if  (location.getLat() != null)
            loc.setLat(location.getLat());
        if  (location.getLng() != null)
            loc.setLng(location.getLng());
        if  (location.getLocationName() != null)
            loc.setLocationName(location.getLocationName());
        locationRepository.save(loc);
        return loc;

    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/location/{id}")
    public String delete(@PathVariable String id) {
        System.out.println("location Server DELETE by id ");
        Location loc = locationRepository.findOne(id);
        locationRepository.delete(loc);

        return "location Deleted";
    }
}