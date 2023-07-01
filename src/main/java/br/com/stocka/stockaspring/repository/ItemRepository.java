package br.com.stocka.stockaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stocka.stockaspring.model.ItemModel;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    
}
