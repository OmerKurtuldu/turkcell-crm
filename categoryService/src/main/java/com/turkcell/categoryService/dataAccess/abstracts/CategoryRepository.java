package com.turkcell.categoryService.dataAccess.abstracts;

import com.turkcell.categoryService.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
