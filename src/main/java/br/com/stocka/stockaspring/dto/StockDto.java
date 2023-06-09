package br.com.stocka.stockaspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StockDto {

    /*@NotBlank(message = "Username must contain a valid value")
    @Size(min = 3, max = 40, message = "Username must be between 3 and 40 characters")
    private String userModel;*/
    
    @NotBlank(message = "Description must contain a valid value")
    @Size(min = 3, max = 40, message = "Description must be between 3 and 40 characters")
    private String description;
    
}
