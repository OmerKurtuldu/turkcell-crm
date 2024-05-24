package com.turkcell.catalogService.api.controllers;

import com.turkcell.catalogService.business.abstacts.ProductService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetProductResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllProductResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/catalogservice/api/v1/products")
public class ProductController {
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse createProduct(@RequestBody CreatedProductRequest createdProductRequest) {
        return productService.addProduct(createdProductRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductResponse updateProduct(@RequestBody UpdatedProductRequest updatedProductRequest){
        return productService.updateProduct(updatedProductRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductResponse getById(@PathVariable int id){
        return productService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductResponse> getAll(){
        return productService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        productService.delete(id);
    }
}
