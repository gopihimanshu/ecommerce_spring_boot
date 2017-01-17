package com.allstate.controllers;

import com.allstate.entities.Product;
import com.allstate.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class ProductController {
    private ProductService service;

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }

    @RequestMapping(value = "/product",method = RequestMethod.POST)
    public Product create(@RequestBody Product product){
        return this.service.create(product);
    }

    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable int id){
        return this.service.findById(id);
    }

    @RequestMapping(value = "/product/search", method = RequestMethod.GET)
    public Product findByName(@RequestParam Map<String,String> queryProduct){
        return this.service.findByName(queryProduct.get("name"));
    }

    @RequestMapping(value = "/product",method = RequestMethod.GET)
    public Iterable<Product> findAll(){
        return this.service.findAll();
    }
    @RequestMapping(value = "/product/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
         this.service.delete(id);
    }
    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public Product update(@PathVariable int id, @RequestBody Product product){
         return this.service.update(id,product);
    }
    @RequestMapping(value = "/product/rating", method = RequestMethod.GET)
    public Integer findAverageRating(){
        return this.service.findAverageRating();
    }

    @RequestMapping(value = "/product/reviews", method = RequestMethod.GET)
    public Integer findByReview(){
        return this.service.findTotalReviews();
    }
}
