package com.turkcell.catalogService.dataAccess.abstracts;

import com.turkcell.catalogService.entities.concretes.Product;
import com.turkcell.catalogService.entities.concretes.ProductFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
