package com.online.shop.system.product.service.dataaccess.adapter;

import com.online.shop.system.product.service.dataaccess.entity.ProductEntity;
import com.online.shop.system.product.service.dataaccess.exception.ResourceNotFoundException;
import com.online.shop.system.product.service.dataaccess.mapper.ProductDataAccessMapper;
import com.online.shop.system.product.service.dataaccess.repository.ProductJpaRepository;
import com.online.shop.system.product.service.domain.dto.create.response.Data;
import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.entity.Product;
import com.online.shop.system.product.service.domain.ports.output.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    private final ProductDataAccessMapper productDataAccessMapper;

    @Override
    public Product createProduct(Product product) {
        return productDataAccessMapper.productEntityToProduct(productJpaRepository.save(productDataAccessMapper.productToProductEntity(product)));
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        ProductEntity productEntity = find(product.getProductID());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        return productDataAccessMapper.productEntityToProduct(productJpaRepository.save(productEntity));
    }

    @Override
    public void deleteProduct(UUID productID) {
        find(productID);
        productJpaRepository.deleteById(productID);
    }

    @Override
    public Product getProduct(UUID productID) {
        return productDataAccessMapper.productEntityToProduct(find(productID));
    }

    @Override
    public List<Product> getAllProduct() {
        return productJpaRepository.findAll().stream().map(productDataAccessMapper::productEntityToProduct).collect(Collectors.toList());
    }

    @Override
    public PagingResponse getProductByCategory(UUID categoryID, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<ProductEntity> pageProducts = productJpaRepository.findByCategoryId(categoryID, paging);
        return PagingResponse.builder()
                .data(pageProducts.getContent().stream().map(productDataAccessMapper::productEntityToProduct).collect(Collectors.toList()))
                .currentPage(pageProducts.getNumber() + 1)
                .size(pageProducts.getSize())
                .total(((int) pageProducts.getTotalElements()))
                .totalPages(pageProducts.getTotalPages())
                .build();
    }

    @Override
    public PagingResponse searchProduct(String productName, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<ProductEntity> pageProducts = productJpaRepository.findByNameContaining(productName, paging);
        return PagingResponse.builder()
                .data(pageProducts.getContent().stream().map(productDataAccessMapper::productEntityToProduct).collect(Collectors.toList()))
                .currentPage(pageProducts.getNumber() + 1)
                .size(pageProducts.getSize())
                .total(((int) pageProducts.getTotalElements()))
                .totalPages(pageProducts.getTotalPages())
                .build();
    }

    @Override
    public Map<UUID, String> checkProductStock(List<Product> products) {
        Map<UUID, String> productWithStatus = new HashMap<>();
        products.forEach(product -> {
            int currentStock = productJpaRepository.getProductQuantity(product.getProductID());
            if(currentStock >= product.getQuantity())
                productWithStatus.put(product.getProductID(), "IN_STOCK");
            if(currentStock < product.getQuantity())
                productWithStatus.put(product.getProductID(), Integer.toString(currentStock));
        });
        return productWithStatus;
    }

    @Override
    public List<Product> getProducts(List<UUID> productIDs) {
        return productJpaRepository.findByIdIn(productIDs).stream().map(productDataAccessMapper::productEntityToProduct).collect(Collectors.toList());
    }

    private ProductEntity find(UUID productID){
        return productJpaRepository.findById(productID).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}
