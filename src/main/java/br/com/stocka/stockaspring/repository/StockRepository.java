package br.com.stocka.stockaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stocka.stockaspring.model.StockModel;

@Repository
public interface StockRepository extends JpaRepository<StockModel, Long> {
    
    Optional<StockModel> findByName(String name);    

    Boolean existsByName(String name);

    Optional<StockModel> findByBarCode(String barCode);    

    Boolean existsByBarCode(String barCode);
    
}
