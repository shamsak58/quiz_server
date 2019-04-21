package com.spring.restapi.dal;


import com.spring.restapi.models.Location;
import com.spring.restapi.models.RadiusMiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationDALImpl implements LocationDAL {

	@Autowired
	private MongoTemplate mongoTemplate;


	//@Override
	public List<RadiusMiles> getLocationRange(Double lat, Double lng) {
		Query query = new Query();
		System.out.println("Start Query Mangodb");
		query.addCriteria(Criteria.where("lat").gt(lat)).addCriteria(Criteria.where("lng").lt(lng));
		System.out.println("Query "+query);
		List<Location> dd = mongoTemplate.find(query, Location.class);
		List<RadiusMiles> resp = new ArrayList();
		for (int i = 0; i < dd.size(); i++) {
			RadiusMiles data = new RadiusMiles();
			data.lat = dd.get(i).lat;
			data.lng = dd.get(i).lng;
			data.locationName = dd.get(i).locationName;
			double dist = getDistance(lat, lng, dd.get(i).lat,dd.get(i).lng);
			data.distance = dist;
			resp.add(data);
			//System.out.println(" Distance "+dist);
		}
		return resp;

	}

	private double getDistance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			return (dist);
		}
	}

}
