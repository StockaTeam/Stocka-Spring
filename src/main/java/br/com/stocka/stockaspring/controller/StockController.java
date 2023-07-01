package br.com.stocka.stockaspring.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

import br.com.stocka.stockaspring.dto.StockDto;
import br.com.stocka.stockaspring.model.StockModel;
import br.com.stocka.stockaspring.service.stock.StockServiceImpl;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/stocks")
public class StockController {
    
    final StockServiceImpl stockServiceImpl;

    public StockController(StockServiceImpl stockServiceImpl) {
        this.stockServiceImpl = stockServiceImpl;
    }    

    @PostMapping
    public ResponseEntity<Object> saveStock(@RequestBody @Valid StockDto stockDto){
        if(stockServiceImpl.existsByDescription(stockDto.getDescription())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Description " + stockDto.getDescription() + " is already in use!");
        }
        
        var stockModel = new StockModel();
        BeanUtils.copyProperties(stockDto, stockModel);
        stockModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(stockServiceImpl.save(stockModel));
    }

    @GetMapping
    public ResponseEntity<List<StockModel>> getAllStocks(){
        return ResponseEntity.status(HttpStatus.OK).body(stockServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneStock(@PathVariable(value = "id") Long id){
        Optional<StockModel> stockModelOptional = stockServiceImpl.findById(id);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(stockModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStock(@PathVariable(value = "id") Long id){
        Optional<StockModel> stockModelOptional = stockServiceImpl.findById(id);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        }
        stockServiceImpl.delete(stockModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Stock deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStock(@PathVariable(value = "id") Long id,
                                                    @RequestBody @Valid StockModel stockDto){
        Optional<StockModel> stockModelOptional = stockServiceImpl.findById(id);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        }
        var stockModel = new StockModel();
        BeanUtils.copyProperties(stockDto, stockModel);
        stockModel.setStockId(stockModelOptional.get().getStockId());        
        return ResponseEntity.status(HttpStatus.OK).body(stockServiceImpl.save(stockModel));
    }

}
