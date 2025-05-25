package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findByNameContainingIgnoreCase(String name);
	
}