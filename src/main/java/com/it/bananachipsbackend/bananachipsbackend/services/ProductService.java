package com.it.bananachipsbackend.bananachipsbackend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.it.bananachipsbackend.bananachipsbackend.entity.ProductEntity;
import com.it.bananachipsbackend.bananachipsbackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Cloudinary cloudinary;

    public ProductEntity addProduct(ProductEntity productEntity, MultipartFile productImage) throws IOException {
        if (productEntity.getProductName() == null || productEntity.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name can't be empty!");
        }
        if (productEntity.getDescription() == null || productEntity.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description can't be empty!");
        }
        if (productEntity.getPrice() == null || productEntity.getPrice().toString().isEmpty()) {
            throw new IllegalArgumentException("Product price can't be empty!");
        }
        if (productEntity.getQuantity() == null || productEntity.getQuantity() <= 0) {
            throw new IllegalArgumentException("Product quantity must be greater than 0!");
        }

        Map uploadImgMap = cloudinary.uploader().upload(productImage.getBytes(), ObjectUtils.emptyMap());
        String imgURL = uploadImgMap.get("url").toString();
        if (imgURL == null || imgURL.isEmpty()) {
            throw new IllegalArgumentException("Product image can't be empty!");
        }
        productEntity.setProductImage(imgURL);
        return productRepository.save(productEntity);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
}