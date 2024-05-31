package com.turkcell.customerService.business.concretes;

import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.customerService.business.rules.CityBusinessRules;
import com.turkcell.customerService.dataAccess.abstracts.CityRepository;
import com.turkcell.customerService.entities.concretes.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityManagerTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityBusinessRules cityBusinessRules;

    @InjectMocks
    private CityManager cityManager;

    @Test
    void getByIdWhenSuccess() {
        // Given
        City city = new City();
        city.setId(54);
        city.setCityName("Sakarya");
        Optional<City> optionalCity = Optional.of(city);

        // When
        doNothing().when(cityBusinessRules).cityShouldBeExist(anyInt());
        when(cityRepository.findById(anyInt())).thenReturn(optionalCity);

        // Then
        City foundCity = cityManager.getById(1);
        Assertions.assertNotNull(foundCity);
        Assertions.assertEquals(city.getCityName(), foundCity.getCityName());
        verify(cityBusinessRules, times(1)).cityShouldBeExist(anyInt());
        verify(cityRepository, times(1)).findById(anyInt());
    }

    @Test
    void getByIdWhenCityDoesNotExist() {
        // When
        doThrow(BusinessException.class).when(cityBusinessRules).cityShouldBeExist(anyInt());

        // Then
        Assertions.assertThrows(BusinessException.class, () -> {
            cityManager.getById(1);
        });
        verify(cityBusinessRules, times(1)).cityShouldBeExist(anyInt());
    }
}
