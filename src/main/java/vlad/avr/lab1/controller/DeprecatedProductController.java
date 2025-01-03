package vlad.avr.lab1.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vlad.avr.lab1.util.PowHelper;

@RestController
public class DeprecatedProductController {

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable int productId) {
        return new Product(productId, PowHelper.pow(productId, productId) + " name");
    }

    @Data
    public static class Product {
        private int id;
        private String name;

        public Product(int id, String name){

            this.id = id;
            this.name = name;
        }
    }
}