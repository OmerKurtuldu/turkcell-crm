package com.turkcell.categoryService.business.dtos.response.create;

import com.turkcell.categoryService.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedCategoryResponse {

    private int id;
    private String name;

}
