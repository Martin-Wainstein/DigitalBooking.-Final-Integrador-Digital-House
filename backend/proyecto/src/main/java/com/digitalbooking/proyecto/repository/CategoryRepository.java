package com.digitalbooking.proyecto.repository;

import com.digitalbooking.proyecto.model.Category;
import com.digitalbooking.proyecto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT COUNT(id) FROM products p WHERE p.id_category = ?1", nativeQuery = true)
    Integer getQuantityOfProductsInCategory(Long id);

}
