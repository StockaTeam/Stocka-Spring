package br.com.stocka.stockaspring.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.stocka.stockaspring.dto.ProductDto;

public class CSVReader {

    public static List<ProductDto> parseProducts(InputStream inputStream) throws IOException {
        List<ProductDto> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                // Extrair os campos do arquivo CSV e criar um objeto ProductDto
                ProductDto product = new ProductDto();
                product.setName(fields[0]);
                product.setType(fields[1]);
                // product.setQuantityInStock(fields[2]);
                product.setPrice(new BigDecimal(fields[3]));
                product.setCompetitionPrice(new BigDecimal(fields[4]));                

                products.add(product);
            }
        }

        return products;
    }
}
