package br.com.stocka.stockaspring.service.product;

import java.util.List;
import java.util.Optional;

import br.com.stocka.stockaspring.model.ProductModel;

public interface ProductService {
    public Long save(ProductModel ProductModel);
    public boolean existsByName(String name);
    public Optional<ProductModel> findByName(String name);    
    public boolean existsByBarCode(String barCode);
    public Optional<ProductModel> findByBarCode(String barCode);   
    public List<ProductModel> findAll();
    public Optional<ProductModel> findById(Long id);    
    public void delete(ProductModel ProductModel);  
}
