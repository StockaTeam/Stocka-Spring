package br.com.stocka.stockaspring.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PRODUCT")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = true)
    private String type;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = true)
    private BigDecimal competition_price;
    @Column(nullable = false)
    private Integer quantityInStock;
    @Column(nullable = true)
    private String barCode;
    @Column(nullable = false)    
    @CreationTimestamp
    private LocalDateTime registrationDate;

    
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getCompetition_price() {
        return competition_price;
    }
    public void setCompetition_price(BigDecimal competition_price) {
        this.competition_price = competition_price;
    }
    public Integer getQuantityInStock() {
        return quantityInStock;
    }
    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    public String getBarCode() {
        return barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    
    
}
