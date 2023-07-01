package br.com.stocka.stockaspring.service.item;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.stocka.stockaspring.model.ItemModel;
import br.com.stocka.stockaspring.repository.ItemRepository;
import jakarta.transaction.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    @Override
    public Long save(ItemModel ItemModel) {
        ItemModel itemCreated = itemRepository.save(ItemModel);        
        return itemCreated.getItemId();
    }

    @Override
    public boolean existsById(Long id) {
        return itemRepository.existsById(id);
    }

    @Override
    public List<ItemModel> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<ItemModel> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(ItemModel ItemModel) {
        itemRepository.delete(ItemModel);
    }
    
}
