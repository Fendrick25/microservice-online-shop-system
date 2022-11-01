package com.online.shop.system.product.review.content.service.dataaccess.repository;

import com.online.shop.system.product.review.content.service.dataaccess.entity.ProductReviewContentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewContentMongoRepository extends MongoRepository<ProductReviewContentEntity, String> {
}
