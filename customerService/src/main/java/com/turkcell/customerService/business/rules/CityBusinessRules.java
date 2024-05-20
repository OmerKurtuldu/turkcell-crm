package com.turkcell.customerService.business.rules;

import com.turkcell.customerService.business.messages.Messages;
import com.turkcell.customerService.dataAccess.abstracts.CityRepository;
import com.turkcell.customerService.entities.concretes.City;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CityBusinessRules {
    private final CityRepository cityRepository;
    private final MessageService messageService;


    public void cityShouldBeExist(int cityId) {
        Optional<City> foundOptionalCity = cityRepository.findById(cityId);
        if (foundOptionalCity.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.CityErrors.CityShouldBeExists));
        }
    }
}
