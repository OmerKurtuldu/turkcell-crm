package com.turkcell.CustomerService.business.abstracts;

import com.turkcell.CustomerService.business.dtos.request.create.CreatedCityRequest;

public interface CityService {
    Integer add(CreatedCityRequest request);
}
