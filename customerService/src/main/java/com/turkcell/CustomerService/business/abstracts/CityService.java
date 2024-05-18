package com.turkcell.CustomerService.business.abstracts;

import com.turkcell.CustomerService.business.dtos.request.create.CreatedCityRequest;
import com.turkcell.CustomerService.entities.concretes.City;

public interface CityService {
    City getById(int id);
}
