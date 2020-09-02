package edu.tcu.cs.hw1problem5.dao;

import edu.tcu.cs.hw1problem5.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, String> {
}
