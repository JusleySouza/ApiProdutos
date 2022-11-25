package com.ju.springweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ju.springweb.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
