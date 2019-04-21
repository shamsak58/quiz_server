package com.spring.restapi.dal;


import com.spring.restapi.models.Location;
import com.spring.restapi.models.RadiusMiles;

import java.util.List;

public interface LocationDAL {


    List<RadiusMiles> getLocationRange(Double lat, Double lng);

}
