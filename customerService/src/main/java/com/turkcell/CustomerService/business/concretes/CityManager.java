package com.turkcell.CustomerService.business.concretes;

import com.turkcell.CustomerService.business.abstracts.CityService;
import com.turkcell.CustomerService.business.dtos.request.create.CreatedCityRequest;
import com.turkcell.CustomerService.dataAccess.abstracts.CityRepository;
import com.turkcell.CustomerService.entities.concretes.City;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;


import java.time.LocalDateTime;

public class CityManager implements CityService {
    private ModelMapperService modelMapperService;
    private CityRepository cityRepository;

    @Override
    public Integer add(CreatedCityRequest request) {
        City city=modelMapperService.forRequest().map(request, City.class);
        city.setCreatedDate(LocalDateTime.now());
        City dbCity=cityRepository.save(city);
        return city.getId();
    }
}
