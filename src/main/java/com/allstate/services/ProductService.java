package com.allstate.services;

import com.allstate.entities.Product;
import com.allstate.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private IProductRepository repository;

    @Autowired
    public void setRepository(IProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product){
        if(product.getPer_off()!=0){
            int actualPrice=product.getList_price()-product.getList_price()*product.getPer_off()/100;
            product.setActual_price(actualPrice);
        }
        return this.repository.save(product);
    }

    public Product findById(int id){
        return this.repository.findOne(id);
    }

    public Product findByName(String productName){ return this.repository.findByName(productName); }

    public Iterable<Product> findAll(){ return this.repository.findAll(); }

    public void delete(int id){
        this.repository.delete(id);
    }

    public Product update(int id,Product product){
        Product productUpdate = this.repository.findOne(id);
        productUpdate.setName(product.getName());
        productUpdate.setStock_number(product.getStock_number());
        productUpdate.setPer_off(product.getPer_off());
        if(productUpdate.getPer_off()!=0){
            int actualPrice=productUpdate.getList_price()-productUpdate.getList_price()*productUpdate.getPer_off()/100;
            productUpdate.setActual_price(actualPrice);
        }
        productUpdate.setList_price(product.getList_price());
        productUpdate.setNo_of_reviews(product.getNo_of_reviews());
        productUpdate.setQuantity(product.getQuantity());
        productUpdate.setRating(product.getRating());
        productUpdate.setList_price(product.getList_price());
        return this.repository.save(productUpdate);
    }

    public Integer findAverageRating(){
        List<Product> productList = (ArrayList<Product>)this.repository.findAll();
        int productAvgRating = productList.stream().map((product)->(product.getRating())).reduce(0,(a,c)->a+c/productList.size());
        return productAvgRating;
    }

    public Integer findTotalReviews(){
        List<Product> productList = (ArrayList<Product>)this.repository.findAll();
        int productTotalReview = productList.stream().map((product)->(product.getNo_of_reviews())).reduce(0,(a,c)->a+c);
        return productTotalReview;
    }

    public Integer findTotalQuantity(){
        List<Product> productList = (ArrayList<Product>)this.repository.findAll();
        int productQuantity = productList.stream().map((product)->(product.getQuantity())).reduce(0,(a,c)->a+c);
        return productQuantity;
    }
}
