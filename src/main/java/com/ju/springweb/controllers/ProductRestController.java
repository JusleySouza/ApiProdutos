package com.ju.springweb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ju.springweb.entities.Product;
import com.ju.springweb.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Product Rest Endpoint")
public class ProductRestController {
	
	@Autowired
	ProductRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	
	@RequestMapping(value="/products/", method=RequestMethod.GET)
	@Operation(summary = "Return multiple products")
	public @ApiResponse(description = "Products Object") List<Product> getProducts(){
		return repository.findAll();
	}

	@RequestMapping(value="/products/{id}", method=RequestMethod.GET)
	@Transactional(readOnly = true)
	@Cacheable("product-cache")
	@Operation(summary = "Returns a product", description = "Takes Id returns single product")
	public @ApiResponse(description = "Product Object") Product getProduct(@Parameter(description = "Id Of The Product") @PathVariable("id") int id) {
		LOGGER.info("Finding product by ID:"+id);
		return repository.findById(id).get();
	}
	
	@RequestMapping(value="/products/", method=RequestMethod.POST)
	@Operation(summary = "Create a product")
	public @ApiResponse(description = "Product Object") Product createProduct(@Valid @RequestBody Product product) {
		return repository.save(product);
	}
	
	@RequestMapping(value="/products/", method=RequestMethod.PUT)
	@Operation(summary = "Update a product")
	public @ApiResponse(description = "Product Object") Product updateProduct(@RequestBody Product product) {
		return repository.save(product);
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.DELETE)
	@CacheEvict("product-cache")
	@Operation(summary = "Delete a product", description = "Takes Id delete single product")
	public void deleteProduct(@Parameter(description = "Id Of The Product") @PathVariable("id") int id) {
		repository.deleteById(id);
	}
	
}
