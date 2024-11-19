package vlad.avr.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching // Увімкнення кешування
public class Lab1Application {

    final static Logger logger = LoggerFactory.getLogger(Lab1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }
}

@Service
class ProductService {

    final static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Cacheable(value = "products", key = "#productId") 
    public String getProductById(String productId) {
        try {
            logger.info("Processing product ID: {}", productId);
            TimeUnit.SECONDS.sleep(10); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Product details for ID: " + productId;
    }
}


@RestController
class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<String> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/something")
    public ResponseEntity<String> createLogs() {
        Lab1Application.logger.warn("Just checking");
        return ResponseEntity.ok().body("All Ok");
    }
}
