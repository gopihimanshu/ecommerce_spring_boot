package com.allstate.services;

import com.allstate.entities.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = "/sql/seed.sql")
public class ProductServiceTest {
    @Autowired
    private ProductService service;

    @Before
    public void setUp() throws Exception {

    }
    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void shouldCreateProductForEcom() throws Exception{
        Product productbfr=new Product();
        productbfr.setName("Smart Watch");
        productbfr.setStock_number("SPEN990");
        productbfr.setList_price(500);
        productbfr.setPer_off(50);
        Product productafr = this.service.create(productbfr);
        assertEquals(2,productafr.getId());
        assertEquals(250,productafr.getActual_price());
    }
    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldThrowException() throws Exception{
        Product productbfr=new Product();
        productbfr.setName("MotoG");
        productbfr.setStock_number("SPEN990");
        productbfr.setList_price(500);
        productbfr.setPer_off(50);
        Product productafr = this.service.create(productbfr);
        assertEquals("MotoG",productafr.getName());
    }
    @Test
    public void shouldReturnProductWithId() throws Exception{
        Product prd=this.service.findById(1);
        assertEquals(1,prd.getId());
        assertEquals("MotoG",prd.getName());
    }

    @Test
    public void shouldReturnProductWithName() throws Exception{
        Product prd=this.service.findByName("MotoG");
        assertEquals(10000,prd.getList_price());
    }

    @Test
    public void shouldReturnAlltheProduct() throws Exception{
        Product productbfr=new Product();
        productbfr.setName("SanDisk Pendrive");
        productbfr.setStock_number("SPEN990");
        this.service.create(productbfr);
        ArrayList<Product> arrayOfProduct =(ArrayList<Product>) this.service.findAll();
        assertEquals(2,arrayOfProduct.size());
    }
    @Test
    public void shouldDeletetheProductById() throws Exception{
        Product productbfr=new Product();
        productbfr.setName("SanDisk Pendrive");
        productbfr.setStock_number("SPEN990");
        this.service.create(productbfr);
        this.service.delete(2);
        Product productaft= this.service.findById(2);
        assertNull(productaft);

    }
}