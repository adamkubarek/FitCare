package pl.javastyle.fitcare.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastyle.fitcare.core.rest.BindingResultExceptionBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;

@RestController
@RequestMapping("api-v1")
class ProductResource {

    private ProductService productService;


    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products/{id}")
    public ResponseEntity getProductByName(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("products")
    public ResponseEntity getAllProducts(@PathParam("sortBy") String sortedBy) {
        return new ResponseEntity<>(productService.getSortedProducts(sortedBy), HttpStatus.OK);
    }

    @PostMapping("products")
    public ResponseEntity addNewProduct(@RequestBody @Valid ProductDTO product, BindingResult result) {
        if (result.hasErrors()) {
            new BindingResultExceptionBuilder(result).buildException();
        }

        ProductDTO savedProduct = productService.addNewProduct(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("products/{productId}")
    public ResponseEntity updateProduct(@RequestBody @Valid ProductDTO product, BindingResult result, @PathVariable Long productId) {
        if (result.hasErrors()) {
            new BindingResultExceptionBuilder(result).buildException();
        }

        return new ResponseEntity<>(productService.updateProduct(product, productId), HttpStatus.OK);
    }

    @PatchMapping("products/{productId}")
    public ResponseEntity patchProduct(@RequestBody ProductDTO product, @PathVariable Long productId) {
        return new ResponseEntity<>(productService.patchProduct(product, productId), HttpStatus.OK);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
