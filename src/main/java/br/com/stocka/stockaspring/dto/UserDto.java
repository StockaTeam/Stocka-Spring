package br.com.stocka.stockaspring.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
    
    @NotBlank(message = "username must contain a valid value")
    @Size(min = 3, max = 40, message = "username must to be between 3 and 40 characters")
    private String username;
    @NotBlank(message = "password must contain a valid value")
    @Size(min = 6, max = 20, message = "password must to be between 6 and 20 characters")
    private String password;
    
    private LocalDateTime registrationDate;
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }    
    
}
