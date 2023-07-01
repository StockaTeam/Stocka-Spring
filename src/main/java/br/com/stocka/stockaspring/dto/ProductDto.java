package br.com.stocka.stockaspring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductDto {

    @NotBlank(message = "username must contain a valid value")
    @Size(min = 3, max = 40, message = "username must to be between 3 and 40 characters")
    private String name;

    private String type;

    private BigDecimal price;

    private Integer quantityInStock;

    private BigDecimal competition_price;

    private String barCode;
    @NotBlank(message = "registration date must contain a valid value")
    @Size(min = 8, max = 10, message = "registration date must to be between 3 and 10 characters")

    private LocalDate expirationDate;

    private String physicalPosition;

    public String getPhysicalPosition() {
        return physicalPosition;
    }

    public void setPhysicalPosition(String physicalPosition) {
        this.physicalPosition = physicalPosition;
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

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
