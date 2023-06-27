package br.com.stocka.stockaspring;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.stocka.stockaspring.model.ProductModel;
import br.com.stocka.stockaspring.repository.ProductRepository;

@SpringBootApplication
public class StockaSpringApplication implements CommandLineRunner {

	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(StockaSpringApplication.class, args);
	}

	public StockaSpringApplication(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		List<ProductModel> products = new ArrayList<>();

		// Criar e adicionar produtos à lista
		products.add(createProduct("1 2 3 E TA PRONTO 300 G", "Alimentos", "5.90", "7.49", "0", 0, "0106JBS3DX"));
		products.add(createProduct("1 2 3 E TA PRONTO 300 G (Duplicado)", "Alimentos", "5.90", "7.99", "0", 0,
				"0109AKWV4U"));
		products.add(createProduct("ABACATE KG", "Alimentos", "3.80", "4.99", "-12.43", -12, "0106LWMAF6"));
		products.add(createProduct("ABACAXI CALDA HELOMAR 400G", "Alimentos", "7.84", "9.99", "0", 0, "0101AWC309"));
		products.add(
				createProduct("ABACAXI CALDA SCHRAMM 400G", "Alimentos", "8.41", "11.99", "1.00", 1, "N5D0000G6B"));
		products.add(createProduct("ABACAXI EM CALDA TRIANGULO MINEIRO 400G", "Alimentos", "6.09", "7.99", "0", 0,
				"0102OK2ND5"));
		products.add(createProduct("ABACAXI UN", "Alimentos", "3.50", "4.99", "11.00", 11, "N5D0000G1T"));
		products.add(createProduct("ABOBORA  C/ COCO CELESTE 300G", "Alimentos", "4.50", "6.49", "0", 0, "0103QM17FD"));
		products.add(createProduct("ABOBORA KG", "Alimentos", "2.50", "3.79", "9383.51", 9384, "0RR0000B6V"));
		products.add(createProduct("ABOBRINHA KG", "Alimentos", "1.50", "4.49", "449.24", 449, "0RR0000BJP"));
		products.add(createProduct("ABRIDOR TRAMONTINA", "Utensílios Domésticos", "24.13", "49.99", "1.00", 1,
				"0109CBG50D"));
		products.add(
				createProduct("ABS ALWAYS PINK NOTURNO C/ ABAS 8UN", "Higiene", "5.22", "7.29", "0", 0, "01004J0Q8M"));
		products.add(createProduct("ABS SEMPRE LIVRE ADAPT 32UN C/ ABAS", "Higiene", "10.20", "14.99", "1.00", 1,
				"010A228R57"));
		products.add(createProduct("ABS SEMPRELIVRE  NOTURNO SECA 8UN C/ABAS", "Higiene", "4.95", "7.49", "0", 0,
				"0101CFWK5Y"));
		products.add(createProduct("ABS. ALWAYS BASICO C/ABAS 8UN SUAVE", "Higiene", "2.28", "3.79", "4.50", 4,
				"010O0NQFVL"));
		products.add(createProduct("ABSORVENTE  INTIMUS GEL 8UN ", "Higiene", "4.80", "6.99", "0", 0, "0109DA1JZJ"));
		products.add(createProduct("ABSORVENTE ALWAYS PINK C/ABAS 8UN NOTURNO", "Higiene", "6.90", "9.99", "0", 0,
				"0109DA1JZM"));
		products.add(
				createProduct("ABSORVENTE INTIMUS GEL NOTURNO 8UN", "Higiene", "6.30", "8.99", "0", 0, "0109DA1JZP"));
		products.add(
				createProduct("ABSORVENTE INTIMUS GEL SUPER 8UN", "Higiene", "4.30", "6.49", "0", 0, "0109DA1JZR"));
		products.add(createProduct("ABSORVENTE INTIMUS GEL SUUPER C/ABAS 8UN", "Higiene", "5.40", "7.49", "0", 0,
				"0109DA1JZT"));
		products.add(createProduct("ABSORVENTE INTIMUS GEL ULTRA FINO NOTURNO 8UN", "Higiene", "6.90", "9.99", "0", 0,
				"0109DA1JZV"));

		// Salvar a lista de produtos
		productRepository.saveAll(products);
	}

	private ProductModel createProduct(String name, String type, String price, String cost, String competitionPrice,
			int quantityInStock, String barCode) {
		ProductModel product = new ProductModel();
		product.setName(name);
		product.setType(type);
		product.setPrice(new BigDecimal(price));
		product.setCost(new BigDecimal(cost));
		product.setCompetition_price(new BigDecimal(competitionPrice));
		product.setQuantityInStock(quantityInStock);
		product.setBarCode(barCode);
		product.setRegistrationDate(LocalDateTime.now());

		return product;
	}

}
