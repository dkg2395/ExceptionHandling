package com.lti.exceptionhandaling.controller;

import com.lti.exceptionhandaling.exception.ProductNotfoundException;
import com.lti.exceptionhandaling.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product product = new Product();
        product.setId("1");
        product.setName("Dell");
        productRepo.put(product.getId(), product);

        Product apple = new Product();
        apple.setId("2");
        apple.setName("Apple");
        productRepo.put(apple.getId(), apple);

        Product Hp = new Product();
        Hp.setId("3");
        Hp.setName("HP");
        productRepo.put(Hp.getId(), Hp);
    }

    @GetMapping("/products")
    public Map<String, Product> getAllProduct(){
        return  productRepo;
    }

    @GetMapping("/products/{id}")
    public boolean getSingleProduct(@PathVariable("id") String id){
        if(!productRepo.containsKey(id))

            throw new ProductNotfoundException();
        return  productRepo.containsKey(id);
    }

    //localhost:8080/products/1
   /* {

        "name": "Honey bean"
    }*/
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id,
                                                @RequestBody Product product) {
        if(!productRepo.containsKey(id))

            throw new ProductNotfoundException();
        //productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }
}