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
    
    @Column(nullable = false, unique = true, name = "name")
    private String name;
    
    @Column(nullable = true, name = "type")
    private String type;
    
    @Column(nullable = false, name = "price")
    private BigDecimal price;
    
    @Column(nullable = true, name = "cost")
    private BigDecimal cost;
    
    @Column(nullable = true, name = "competition_price")
    private BigDecimal competitionPrice;
    
    @Column(nullable = false, name = "quantity_in_stock")
    private Integer quantityInStock;
    
    @Column(nullable = false, name = "bar_code")
    private String barCode;
    
    @Column(nullable = false, name = "expiration_date")
    private LocalDate expirationDate;

    @Column(nullable = false, name = "physical_position")
    private String physicalPosition;
    
    @Column(nullable = true, name = "registration_date")
    @CreationTimestamp
    private LocalDateTime registrationDate;
}
