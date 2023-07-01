package br.com.stocka.stockaspring.model;

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
@Table(name = "TB_STOCK")
public class StockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockId;
    
    @Column(nullable = false, unique = true, name = "description")
    private String description;
    
    @Column(nullable = false, name = "user_model")
    private UserModel userModel;
    
    @Column(nullable = false, name = "registration_date")    
    @CreationTimestamp
    private LocalDateTime registrationDate; 
    
}
