package huy.java.server.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import huy.java.server.product.entity.Product;
import huy.java.server.product.exception.ProductNotFoundException;
import huy.java.server.product.repo.ProductRepository;

@RestController
public class ProductController {
	private final ProductRepository repository;
	
	public ProductController(ProductRepository pr) {
		this.repository = pr;
	}
	
	// get all products
	@GetMapping("/products")
	List<Product> getAllProducts() {
		return (List<Product>) repository.findAll();
	}
	
	// add new product to database
	@PostMapping("/products")
	Product addNewProduct(@RequestBody Product product) {
		return repository.save(product);
	}
	
	// get one product by ID
	@GetMapping("/products/{id}")
	Product getOneProduct(@PathVariable int id) {
		return repository.findById(id).orElseThrow(
			() -> new ProductNotFoundException(id)
		);
	}
	// update one product by ID
	@PutMapping("/products/{id}")
	Product updateOneProduct(@RequestBody Product newProduct, @PathVariable int id) {
		return repository.findById(id)
				.map(product -> {
					product.setName(newProduct.getName());
					product.setPrice(newProduct.getPrice());
					product.setDesc(newProduct.getDesc());
					return repository.save(product);
				})
				.orElseGet(() -> {
					newProduct.setId(id);
					return repository.save(newProduct);
				});
	}
	
	// delete one employee by ID
	@DeleteMapping("/products/{id}")
	void deleteProduct(@PathVariable int id) {
		repository.deleteById(id);
	}
}
