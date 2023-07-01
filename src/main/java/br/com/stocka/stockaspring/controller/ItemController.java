package br.com.stocka.stockaspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.stocka.stockaspring.dto.ItemDto;
import br.com.stocka.stockaspring.model.ItemModel;
import br.com.stocka.stockaspring.service.item.ItemServiceImpl;
import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/items")
public class ItemController {

    final ItemServiceImpl itemServiceImpl;

    public ItemController(ItemServiceImpl itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    } 

    @PostMapping
    public ResponseEntity<Object> saveItem(@RequestBody @Valid ItemDto itemDto){
        /*if(itemServiceImpl.existsById(itemDto.get())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Description " + itemDto.getDescription() + " is already in use!");
        }*/
        
        var stockModel = new ItemModel();
        BeanUtils.copyProperties(itemDto, stockModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemServiceImpl.save(stockModel));
    }

    @GetMapping
    public ResponseEntity<List<ItemModel>> getAllItems(){
        return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneItem(@PathVariable(value = "id") Long id){
        Optional<ItemModel> stockModelOptional = itemServiceImpl.findById(id);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(stockModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "id") Long id){
        Optional<ItemModel> stockModelOptional = itemServiceImpl.findById(id);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
        itemServiceImpl.delete(stockModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable(value = "id") Long id,
                                                    @RequestBody @Valid ItemModel itemDto){
        Optional<ItemModel> stockModelOptional = itemServiceImpl.findById(id);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
        var stockModel = new ItemModel();
        BeanUtils.copyProperties(itemDto, stockModel);
        stockModel.setItemId(stockModelOptional.get().getItemId());        
        return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.save(stockModel));
    }
    
}
