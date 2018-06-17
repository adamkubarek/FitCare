package pl.javastyle.fitcare.services.mappers;

import pl.javastyle.fitcare.domain.Product;
import pl.javastyle.fitcare.rest.dto.ProductDTO;

public interface ProductMapper {
    Product dtoToProduct(ProductDTO productDTO);
    ProductDTO productToDto(Product product);
}
