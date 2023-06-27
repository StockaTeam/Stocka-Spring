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

import br.com.stocka.stockaspring.dto.ProductDto;
import br.com.stocka.stockaspring.model.ProductModel;
import br.com.stocka.stockaspring.service.product.ProductServiceImpl;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")
public class ProductController {
    
    final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }    

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto){
        if(productServiceImpl.existsByName(productDto.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name " + productDto.getName() + " is already in use!");
        }
        
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        productModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(productServiceImpl.save(productModel));
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id){
        Optional<ProductModel> ProductModelOptional = productServiceImpl.findById(id);
        if (!ProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProductModelOptional.get());
    }

    @GetMapping("/barCode/{barCode}")
    public ResponseEntity<Object> getOneProductByBarCode(@PathVariable(value = "barCode") String barCode){
        Optional<ProductModel> ProductModelOptional = productServiceImpl.findByBarCode(barCode);
        if (!ProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BarCode not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProductModelOptional.get());
    }
    
    // @GetMapping("/barCode/{barCode}")
    // public ResponseEntity<Object> getOneProduct(@PathVariable(value = "barCode") String barCode){
    //     Optional<ProductModel> ProductModelOptional = productServiceImpl.findByBarCode(barCode);
    //     if (!ProductModelOptional.isPresent()) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
    //     }
    //     return ResponseEntity.status(HttpStatus.OK).body(ProductModelOptional.get());
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id){
        Optional<ProductModel> ProductModelOptional = productServiceImpl.findById(id);
        if (!ProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productServiceImpl.delete(ProductModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id,
                                                    @RequestBody @Valid ProductModel productDto){
        Optional<ProductModel> ProductModelOptional = productServiceImpl.findById(id);
        if (!ProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var ProductModel = new ProductModel();
        BeanUtils.copyProperties(productDto, ProductModel);
        ProductModel.setProductId(ProductModelOptional.get().getProductId());        
        return ResponseEntity.status(HttpStatus.OK).body(productServiceImpl.save(ProductModel));
    }

}
