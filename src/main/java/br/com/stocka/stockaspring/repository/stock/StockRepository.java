package br.com.stocka.stockaspring.repository.stock;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stocka.stockaspring.model.StockModel;

@Repository
public interface StockRepository extends JpaRepository<StockModel, Long> {
    
    Optional<StockModel> findByDescription(String description);    

    Boolean existsByDescription(String description);
    
}
