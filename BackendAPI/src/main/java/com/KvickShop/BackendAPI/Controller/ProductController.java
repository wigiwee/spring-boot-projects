package com.KvickShop.BackendAPI.Controller;

import com.KvickShop.BackendAPI.Model.Product;
import com.KvickShop.BackendAPI.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){

        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @RequestMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = service.getProductById(id);

        if(product!= null){
            return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
