package br.com.stocka.stockaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stocka.stockaspring.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    
    Optional<ProductModel> findByName(String name);    
    Boolean existsByName(String name);

    Optional<ProductModel> findByBarCode(String barCode);    
    Boolean existsByBarCode(String barCode);
    
}
