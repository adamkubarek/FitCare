package pl.javastyle.fitcare.services.mappers;

import org.springframework.stereotype.Component;
import pl.javastyle.fitcare.domain.Category;
import pl.javastyle.fitcare.domain.Product;
import pl.javastyle.fitcare.rest.dto.ProductDTO;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {

    @Override
    public Product dtoToDomain(ProductDTO productDTO) {
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCalories(productDTO.getCalories());
        product.setCarbs(productDTO.getCarbs());
        product.setProtein(productDTO.getProtein());
        product.setFat(productDTO.getFat());

        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public ProductDTO domainToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setCalories(product.getCalories());
        productDTO.setCarbs(product.getCarbs());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setFat(product.getFat());
        productDTO.setName(product.getName());
        productDTO.setProtein(product.getProtein());

        return productDTO;
    }
}
