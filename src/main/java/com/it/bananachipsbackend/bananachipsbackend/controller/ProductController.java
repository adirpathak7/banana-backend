package com.it.bananachipsbackend.bananachipsbackend.controller;

import com.it.bananachipsbackend.bananachipsbackend.entity.ProductEntity;
import com.it.bananachipsbackend.bananachipsbackend.services.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = {"https://webbanana-eight.vercel.app", "http://localhost:3000"}, allowCredentials = "true")
@RequestMapping(value = "/banana/app")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/admin/product/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> addProduct(
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("price") Long price,
            @RequestParam("quantity") Long quantity,
            @RequestParam(value = "category", required = false, defaultValue = "Un-Categorized") String category,
            @RequestParam("productImage") MultipartFile productImage) throws IOException {

        ProductEntity productEntity = new ProductEntity(productName, description, price, quantity, category);
        productService.addProduct(productEntity, productImage);

        Map<String, String> signUpResponse = new HashMap<>();
        signUpResponse.put("message", "Product added successfully.");
        signUpResponse.put("data", "1");

        return new ResponseEntity<>(signUpResponse, HttpStatus.OK);
    }

    @GetMapping("/admin/product/allProducts")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}