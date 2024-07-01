package com.KvickShop.BackendAPI.Service;

import com.KvickShop.BackendAPI.Model.Product;
import com.KvickShop.BackendAPI.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;


    public List<Product> getAllProducts() {

        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }
}
