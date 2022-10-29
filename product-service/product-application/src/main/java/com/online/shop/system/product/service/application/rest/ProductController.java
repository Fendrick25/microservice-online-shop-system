package com.online.shop.system.product.service.application.rest;

import com.online.shop.system.product.service.domain.dto.create.CreateProduct;
import com.online.shop.system.product.service.domain.dto.create.UpdateProduct;
import com.online.shop.system.product.service.domain.dto.create.response.Data;
import com.online.shop.system.product.service.domain.dto.create.response.GetProductResponse;
import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.create.response.ProductIDResponse;
import com.online.shop.system.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductApplicationService productApplicationService;

    @PostMapping
    public ResponseEntity<Data<ProductIDResponse>> createProduct(@RequestBody CreateProduct createProduct){
        return new ResponseEntity<>(new Data<>(productApplicationService.createProduct(createProduct)), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Data<ProductIDResponse>> updateProduct(@RequestBody UpdateProduct updateProduct){
        return new ResponseEntity<>(new Data<>(productApplicationService.updateProduct(updateProduct)), HttpStatus.OK);
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<Data<ProductIDResponse>> deleteProduct(@PathVariable("productID") UUID productID){
        return new ResponseEntity<>(new Data<>(productApplicationService.deleteProduct(productID)), HttpStatus.OK);
    }

    @GetMapping("/{productID}")
    public ResponseEntity<Data<GetProductResponse>> getProduct(@PathVariable("productID") UUID productID){
        return new ResponseEntity<>(new Data<>(productApplicationService.getProduct(productID)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Data<List<GetProductResponse>>> getAllProduct(){
        return new ResponseEntity<>(new Data<>(productApplicationService.getAllProduct()), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryID}")
    public ResponseEntity<PagingResponse> getProductByCategory(@PathVariable("categoryID") UUID categoryID,
                                                               @RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(productApplicationService.getProductByCategory(categoryID, page, size), HttpStatus.OK);
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<PagingResponse> searchProduct(@PathVariable("productName") String productName,
                                                        @RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(productApplicationService.searchProduct(productName, page, size), HttpStatus.OK);
    }
}
