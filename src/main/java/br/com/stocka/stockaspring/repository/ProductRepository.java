package br.com.stocka.stockaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stocka.stockaspring.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByName(String name);
    Boolean existsByName(String name);
    Optional<ProductModel> findByBarCode(String barCode);
    Boolean existsByBarCode(String barCode);

    /*
     * @Query("SELECT p FROM ProductModel p WHERE p.expirationDate = :expirationDate"
     * )
     * List<ProductModel> findByExpirationDate(LocalDate expirationDate, LocalDate
     * firstDate, LocalDate lastDate);
     */
}
