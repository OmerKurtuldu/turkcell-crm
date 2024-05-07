package com.turkcell.accountService.dataAccess.abstracts;


import com.turkcell.accountService.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
}
