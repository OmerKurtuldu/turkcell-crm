package com.turkcell.catalogService.dataAccess.abstracts;

import com.turkcell.catalogService.entities.concretes.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    Optional<Feature> findByName(String name);
}
