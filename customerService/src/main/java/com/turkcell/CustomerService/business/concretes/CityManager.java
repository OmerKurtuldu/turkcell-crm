package com.turkcell.CustomerService.business.concretes;

import com.turkcell.CustomerService.business.abstracts.CityService;
import com.turkcell.CustomerService.business.dtos.request.create.CreatedCityRequest;
import com.turkcell.CustomerService.business.rules.CityBusinessRules;
import com.turkcell.CustomerService.dataAccess.abstracts.CityRepository;
import com.turkcell.CustomerService.entities.concretes.City;
import com.turkcell.CustomerService.entities.concretes.Customer;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CityManager implements CityService {
    private final CityRepository cityRepository;
    private final CityBusinessRules cityBusinessRules;


    @Override
    public City getById(int id) {
        cityBusinessRules.cityShouldBeExist(id);
        Optional<City> city = cityRepository.findById(id);
        return city.get();
    }


}
