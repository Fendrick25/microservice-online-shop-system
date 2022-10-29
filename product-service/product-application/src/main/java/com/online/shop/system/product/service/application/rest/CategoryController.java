package com.online.shop.system.product.service.application.rest;


import com.online.shop.system.product.service.domain.dto.create.CreateCategory;
import com.online.shop.system.product.service.domain.dto.create.UpdateCategory;
import com.online.shop.system.product.service.domain.dto.create.response.CategoryIDResponse;
import com.online.shop.system.product.service.domain.dto.create.response.Data;
import com.online.shop.system.product.service.domain.dto.create.response.GetCategoryResponse;
import com.online.shop.system.product.service.domain.ports.input.service.CategoryApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryApplicationService categoryApplicationService;

    @PostMapping
    public ResponseEntity<Data<CategoryIDResponse>> createCategory(@RequestBody CreateCategory createCategory){
        return new ResponseEntity<>(new Data<>(categoryApplicationService.createCategory(createCategory)), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Data<CategoryIDResponse>> updateCategory(@RequestBody UpdateCategory updateCategory){
        return new ResponseEntity<>(new Data<>(categoryApplicationService.updateCategory(updateCategory)), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryID}")
    public ResponseEntity<Data<CategoryIDResponse>> deleteCategory(@PathVariable("categoryID") UUID categoryID){
        return new ResponseEntity<>(new Data<>(categoryApplicationService.deleteCategory(categoryID)), HttpStatus.OK);
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<Data<GetCategoryResponse>> getCategory(@PathVariable("categoryID") UUID categoryID){
        return new ResponseEntity<>(new Data<>(categoryApplicationService.getCategory(categoryID)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Data<List<GetCategoryResponse>>> getAllCategory(){
        return new ResponseEntity<>(new Data<>(categoryApplicationService.getAllCategory()), HttpStatus.OK);
    }
}
