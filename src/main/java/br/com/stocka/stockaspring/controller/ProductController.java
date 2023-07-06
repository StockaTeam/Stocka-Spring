package br.com.stocka.stockaspring.controller;

import java.io.IOException;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.stocka.stockaspring.dto.CompetitionPriceProductDto;
import br.com.stocka.stockaspring.dto.ProductDto;
import br.com.stocka.stockaspring.helper.CSVReader;
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

    // localhost:8080/products/upload
    @PostMapping("/upload")
    public ResponseEntity<Object> uploadProducts(@RequestParam("file") MultipartFile file) {
        // Verificar se o arquivo é um arquivo CSV
        if (!file.getContentType().equals("text/csv")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo deve ser do tipo CSV.");
        }

        try {
            // Ler o arquivo CSV e processar os dados
            List<ProductDto> products = CSVReader.parseProducts(file.getInputStream());

            // Salvar os produtos no banco de dados
            productServiceImpl.saveAll(products);

            return ResponseEntity.status(HttpStatus.CREATED).body("Produtos cadastrados com sucesso.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo CSV.");
        }
    }

    // localhost:8080/products
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto) {
        if (productServiceImpl.existsByName(productDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict: Name " + productDto.getName() + " is already in use!");
        }

        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        productModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(productServiceImpl.save(productModel));
    }

    // localhost:8080/products
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productServiceImpl.findAll());
    }

    // localhost:8080/products/expiringDate/2023-06-30
    @GetMapping("/expiringDate/{finishDate}")
    public ResponseEntity<List<ProductModel>> getAllProductsExpiringDate(@PathVariable(value = "finishDate") LocalDate finishDate) {
        return ResponseEntity.status(HttpStatus.OK).body(productServiceImpl.findByExpirationDateLessThanEqual(finishDate));
    }

    // localhost:8080/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id) {
        Optional<ProductModel> ProductModelOptional = productServiceImpl.findById(id);
        if (!ProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProductModelOptional.get());
    }

    // localhost:8080/products/barCode/{barCode}
    @GetMapping("/barCode/{barCode}")
    public ResponseEntity<Object> getOneProductByBarCode(@PathVariable(value = "barCode") String barCode) {
        Optional<ProductModel> ProductModelOptional = productServiceImpl.findByBarCode(barCode);
        if (!ProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BarCode not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProductModelOptional.get());
    }

    // localhost:8080/products/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id) {
        Optional<ProductModel> ProductModelOptional = productServiceImpl.findById(id);
        if (!ProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productServiceImpl.delete(ProductModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    // localhost:8080/products/1
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id,
            @RequestBody @Valid ProductModel productDto) {
        Optional<ProductModel> ProductModelOptional = productServiceImpl.findById(id);
        if (!ProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var ProductModel = new ProductModel();
        BeanUtils.copyProperties(productDto, ProductModel);
        ProductModel.setProductId(ProductModelOptional.get().getProductId());
        return ResponseEntity.status(HttpStatus.OK).body(productServiceImpl.save(ProductModel));
    }

    // localhost:8080/products/1/competitionprice
    @PatchMapping("/{id}/competitionprice")
    public ResponseEntity<Object> updateCompetitionPriceOfProduct(@PathVariable(value = "id") Long id,
            @RequestBody @Valid CompetitionPriceProductDto competitionPriceProductDto) {
        Optional<ProductModel> ProductModelOptional = productServiceImpl.findById(id);
        if (!ProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var ProductModel = new ProductModel();
        BeanUtils.copyProperties(competitionPriceProductDto, ProductModel);
        ProductModel.setProductId(ProductModelOptional.get().getProductId());
        return ResponseEntity.status(HttpStatus.OK).body(productServiceImpl.save(ProductModel));
    }

}
