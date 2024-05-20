package com.turkcell.customerService.business.concretes;

import com.turkcell.customerService.business.abstracts.CityService;
import com.turkcell.customerService.business.rules.CityBusinessRules;
import com.turkcell.customerService.dataAccess.abstracts.CityRepository;
import com.turkcell.customerService.entities.concretes.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
