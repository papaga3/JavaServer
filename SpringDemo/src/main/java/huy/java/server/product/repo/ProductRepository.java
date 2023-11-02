package huy.java.server.product.repo;

import org.springframework.data.repository.CrudRepository;

import huy.java.server.product.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
