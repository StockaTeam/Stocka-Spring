package br.com.stocka.stockaspring.service.item;

import java.util.List;
import java.util.Optional;

import br.com.stocka.stockaspring.model.ItemModel;

public interface ItemService {
    public Long save(ItemModel ItemModel);
    public boolean existsById(Long id);
    public List<ItemModel> findAll();
    public Optional<ItemModel> findById(Long id);    
    public void delete(ItemModel ItemModel);  
}
