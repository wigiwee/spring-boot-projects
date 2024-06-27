package com.KvickShop.BackendAPI.Service;

import com.KvickShop.BackendAPI.Model.Product;
import com.KvickShop.BackendAPI.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
