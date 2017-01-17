package com.allstate.controllers;

import com.allstate.entities.Product;
import com.allstate.services.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    private Product product;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setId(2);
        product.setName("pendrive");
        product.setStock_number("PEN9982");
        product.setList_price(500);
        product.setPer_off(10);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateProduct() throws Exception{
        given(this.service.create(Mockito.any(Product.class)))
                .willReturn(product);
        MockHttpServletRequestBuilder request = post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"motox\",\"name\": \"motox\",\"stock_number\": \"motox2234\",\"list_price\": 100000}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(2)))
                .andExpect(jsonPath("$.name",is("pendrive")));
    }

    @Test
    public void shouldfindProductById() throws Exception{
        given(this.service.findById(1))
                .willReturn(product);

        MockHttpServletRequestBuilder request = get("/product/1");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("pendrive")));
    }

    @Test
    public void shouldFindByName() throws Exception{
        given(this.service.findByName("pendrive"))
                .willReturn(product);

        MockHttpServletRequestBuilder request = get("/product/search?name=pendrive");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("pendrive")));
    }

    @Test
    public void shouldDeleteById() throws Exception{
       MockHttpServletRequestBuilder request = delete("/product/1");

        this.mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateProduct() throws Exception {
        product.setList_price(800);

        given(this.service.update(anyInt(), Mockito.any(Product.class)))
                .willReturn(product);

        MockHttpServletRequestBuilder request = put("/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"list_price\": 800}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list_price", is(800)));
    }
}