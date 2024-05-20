package com.turkcell.customerService.business.abstracts;

import com.turkcell.customerService.entities.concretes.City;

public interface CityService {
    City getById(int id);
}
