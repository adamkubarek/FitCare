package pl.javastyle.fitcare.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastyle.fitcare.exceptions.ApplicationException;
import pl.javastyle.fitcare.exceptions.ValidationErrors;
import pl.javastyle.fitcare.rest.dto.ProductDTO;
import pl.javastyle.fitcare.services.interfaces.ProductService;

import javax.validation.Valid;
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
    public ResponseEntity addNewProduct(@RequestBody @Valid ProductDTO product, BindingResult result) {
        if (result.hasErrors()) {
            throwExceptionWithProperMessage(result);
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
            throwExceptionWithProperMessage(result);
        }
        return new ResponseEntity<>(productService.updateProduct(product, productId), HttpStatus.OK);
    }

    private void throwExceptionWithProperMessage(BindingResult result) {
        String errorMessage = buildMessageFromBindingResult(result);

        ValidationErrors.NOT_VALID.setDescription(errorMessage);
        throw new ApplicationException(ValidationErrors.NOT_VALID);
    }

    private String buildMessageFromBindingResult(BindingResult result) {
        StringBuilder allErrorMessages = new StringBuilder();

        for (ObjectError error : result.getAllErrors()) {
            allErrorMessages
                    .append("Given field '")
                    .append(((FieldError)error).getField())
                    .append("' ")
                    .append(error.getDefaultMessage())
                    .append("\n\n");
        }

        return allErrorMessages.toString();
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
