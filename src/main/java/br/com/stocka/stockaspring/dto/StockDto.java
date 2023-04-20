package br.com.stocka.stockaspring.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StockDto {

    @NotBlank(message = "username must contain a valid value")
    @Size(min = 3, max = 40, message = "username must to be between 3 and 40 characters")
    private String user;
    @NotBlank(message = "description must contain a valid value")
    @Size(min = 3, max = 40, message = "description must to be between 3 and 40 characters")
    private String description;
    @NotBlank(message = "registration date must contain a valid value")
    @Size(min = 8, max = 10, message = "registration date must to be between 3 and 10 characters")
    private LocalDateTime registrationDate;
    
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}
