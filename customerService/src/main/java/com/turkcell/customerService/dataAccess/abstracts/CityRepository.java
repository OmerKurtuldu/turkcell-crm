package com.turkcell.customerService.dataAccess.abstracts;

import com.turkcell.customerService.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
}
