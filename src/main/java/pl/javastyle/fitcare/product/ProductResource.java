package pl.javastyle.fitcare.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.javastyle.fitcare.core.rest.BindingResultExceptionBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api-v1")
class ProductResource {

    private ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity getProductByName(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("products")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity getAllProducts(@PathParam("sortBy") String sortedBy) {
        return new ResponseEntity<>(productService.getSortedProducts(sortedBy), HttpStatus.OK);
    }

    @PostMapping("products")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity addNewProduct(@RequestBody @Valid ProductDTO product, BindingResult result) {
        if (result.hasErrors()) {
            new BindingResultExceptionBuilder(result).buildException();
        }

        return new ResponseEntity<>(productService.addNewProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("products/{productId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity updateProduct(@RequestBody @Valid ProductDTO product, BindingResult result, @PathVariable Long productId) {
        if (result.hasErrors()) {
            new BindingResultExceptionBuilder(result).buildException();
        }

        return new ResponseEntity<>(productService.updateProduct(product, productId), HttpStatus.OK);
    }

    @PatchMapping("products/{productId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity patchProduct(@RequestBody ProductDTO product, @PathVariable Long productId) {
        return new ResponseEntity<>(productService.patchProduct(product, productId), HttpStatus.OK);
    }

    @DeleteMapping("products/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
