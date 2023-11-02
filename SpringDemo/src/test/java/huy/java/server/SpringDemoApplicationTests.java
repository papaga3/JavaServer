package huy.java.server;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import huy.java.server.config.H2TestConfig;
import huy.java.server.product.entity.Product;
import huy.java.server.product.repo.ProductRepository;

@SpringBootTest(classes= {
	SpringDemoApplication.class,
	H2TestConfig.class
})
@ActiveProfiles("test")
class SpringDemoApplicationTests {
	@Autowired
	ProductRepository repository;
	
	@Test
	void contextLoads() {
	}

    @Test
    void testCreate() {
		Product p = new Product(1, "phone", "good", 1500.0);
		repository.save(p);
	}

    @Test
    void testRead() {
    	Product p = new Product(1, "phone", "good", 1500.0);
		repository.save(p);
		p = repository.findById(1).get();
		assertThat(p).isNotNull();
		assertThat(p.getName()).isEqualTo("phone");
	}

    @Test
    void testUpdate() {
		Product p = repository.findById(1).get();
		p.setPrice(0);
		repository.save(p);
		p = repository.findById(1).get();
		assertThat(p).isNotNull();
		assertThat(p.getPrice()).isEqualTo(0);
	}
}
