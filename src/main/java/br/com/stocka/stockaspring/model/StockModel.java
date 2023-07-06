package br.com.stocka.stockaspring.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_STOCK")
public class StockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockId;
    
    @Column(nullable = true, unique = true, name = "description")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private List<ProductModel> listProducts;

    @Column(nullable = true, name = "registration_date") 
    @JsonFormat(pattern = "dd/MM/yyyy")   
    @CreationTimestamp
    private LocalDateTime registrationDate; 
}

