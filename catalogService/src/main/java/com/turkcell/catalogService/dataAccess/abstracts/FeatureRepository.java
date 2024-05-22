package com.turkcell.catalogService.dataAccess.abstracts;

import com.turkcell.catalogService.entities.concretes.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {
}
