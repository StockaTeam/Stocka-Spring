package br.com.stocka.stockaspring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddProductInStock {
    @NotNull(message = "Product ID must contain a valid value")
    private Long productId;
}
