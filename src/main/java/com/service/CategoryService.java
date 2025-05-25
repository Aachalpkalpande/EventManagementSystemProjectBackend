package com.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.model.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	Category getCategoryById(Long id); 
	Category createCategory(Category category); 
	Category updateCategory(Long id, Category category);
	void deleteCategory(Long id); 
	//Page<Category> getAllCategories(Pageable pageable);
	List<Category> searchByName(String name);
}