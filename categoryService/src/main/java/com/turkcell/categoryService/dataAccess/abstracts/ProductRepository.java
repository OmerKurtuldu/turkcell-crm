package com.turkcell.categoryService.dataAccess.abstracts;

import com.turkcell.categoryService.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
