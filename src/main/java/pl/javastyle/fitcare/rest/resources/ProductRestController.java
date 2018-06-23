package pl.javastyle.fitcare.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastyle.fitcare.rest.dto.ProductDTO;
import pl.javastyle.fitcare.services.interfaces.ProductService;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api-v1")
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products/{id}")
    public ResponseEntity getProductByName(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("products")
    public ResponseEntity getAllProducts(@PathParam("sortBy") String sortedBy) {
        List<ProductDTO> allProducts = productService.getAllProducts();

        allProducts = pickSortMethod(sortedBy, allProducts);

        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    private List<ProductDTO> pickSortMethod(String sortedBy, List<ProductDTO> allProducts) {
        if ("name".equals(sortedBy)) {
            allProducts = productService.sortAllProductsByName(allProducts);
        } else if ("calories".equals(sortedBy)) {
            allProducts = productService.sortAllProductsByCalories(allProducts);
        } else if ("category".equals(sortedBy)) {
            allProducts = productService.sortAllProductsByCategory(allProducts);
        }

        return allProducts;
    }

    @PostMapping("products")
    public ResponseEntity addNewProduct(@RequestBody ProductDTO product) {
        ProductDTO savedProduct = productService.addNewProduct(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("products/{productId}")
    public ResponseEntity updateProduct(@RequestBody ProductDTO product, @PathVariable Long productId) {
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
