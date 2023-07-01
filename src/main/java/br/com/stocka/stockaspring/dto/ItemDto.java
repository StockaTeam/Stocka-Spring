package br.com.stocka.stockaspring.dto;

import java.util.List;

import br.com.stocka.stockaspring.model.ProductModel;
import br.com.stocka.stockaspring.model.StockModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemDto {

    @NotNull(message = "Stock in stock must contain a valid value")
    private List<StockModel> stocks;

    @NotNull(message = "Product in stock must contain a valid value")
    private List<ProductModel> products;

}
