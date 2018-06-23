package pl.javastyle.fitcare.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastyle.fitcare.rest.dto.ProductDTO;
import pl.javastyle.fitcare.services.interfaces.ProductService;

import java.net.URI;

@RestController
@RequestMapping("api-v1")
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("products")
    public ResponseEntity addNewProduct (@RequestBody ProductDTO product) {
        ProductDTO savedProduct = productService.addNewProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("products/{productId}")
    public ResponseEntity updateProduct (@RequestBody ProductDTO product, @PathVariable Long productId) {
        ProductDTO updatedProduct = productService.updateProduct(product, productId);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
