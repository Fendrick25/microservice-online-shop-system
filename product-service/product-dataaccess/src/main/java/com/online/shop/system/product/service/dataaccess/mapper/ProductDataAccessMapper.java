package com.online.shop.system.product.service.dataaccess.mapper;

import com.online.shop.system.product.service.dataaccess.entity.ProductEntity;
import com.online.shop.system.product.service.dataaccess.entity.ProductRatingEntity;
import com.online.shop.system.product.service.domain.dto.message.ProductRating;
import com.online.shop.system.product.service.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDataAccessMapper {

    private final CategoryDataAccessMapper categoryDataAccessMapper;

    public Product productEntityToProduct(ProductEntity productEntity){
        return Product.builder()
                .productID(productEntity.getProductID())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .productStatus(productEntity.getProductStatus())
                .category(categoryDataAccessMapper.categoryEntityToCategory(productEntity.getCategory()))
                .imageUrl(productEntity.getImageUrl())
                .rating(productEntity.getRating())
                .soldAmount(productEntity.getSoldAmount())
                .build();
    }

    public ProductEntity productToProductEntity(Product product){
        return ProductEntity.builder()
                .productID(product.getProductID())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .category(categoryDataAccessMapper.categoryToCategoryEntity(product.getCategory()))
                .imageUrl(product.getImageUrl())
                .rating(product.getRating())
                .soldAmount(product.getSoldAmount())
                .build();
    }

    public ProductRatingEntity productRatingToProductRatingEntity(ProductRating productRating){
        return ProductRatingEntity.builder()
                .rating(productRating.getRating())
                .build();
    }
}
