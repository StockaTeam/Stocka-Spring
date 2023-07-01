package br.com.stocka.stockaspring.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PRODUCT")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    
    @Column(nullable = true, unique = true, name = "name")
    private String name;
    
    @Column(nullable = true, name = "type")
    private String type;
    
    @Column(nullable = true, name = "price")
    private BigDecimal price;
    
    @Column(nullable = true, name = "cost")
    private BigDecimal cost;
    
    @Column(nullable = true, name = "competition_price")
    private BigDecimal competition_price;
    
    @Column(nullable = true, name = "quantityInStock")
    private Integer quantityInStock;
    
    @Column(nullable = true, name = "barCode")
    private String barCode;
    
    @Column(nullable = true, name = "expirationDate")
    private LocalDate expirationDate;

    @Column(nullable = true, name = "physicalPosition")
    private String physicalPosition;
    
    @Column(nullable = true, name = "registrationDate")
    @CreationTimestamp
    private LocalDateTime registrationDate;
}
