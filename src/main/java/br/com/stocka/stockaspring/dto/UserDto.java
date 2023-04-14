package br.com.stocka.stockaspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
    
    @NotBlank
    @Size(min = 3, max = 40)
    private String username;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    
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
