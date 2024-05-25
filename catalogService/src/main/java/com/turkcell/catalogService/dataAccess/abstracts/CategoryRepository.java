package com.turkcell.catalogService.dataAccess.abstracts;

import com.turkcell.catalogService.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findByCategoryName(String name);
}
