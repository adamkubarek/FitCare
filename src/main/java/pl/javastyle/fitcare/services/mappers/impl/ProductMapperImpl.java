package pl.javastyle.fitcare.services.mappers.impl;

import org.springframework.stereotype.Component;
import pl.javastyle.fitcare.domain.Category;
import pl.javastyle.fitcare.domain.Product;
import pl.javastyle.fitcare.rest.dto.ProductDTO;
import pl.javastyle.fitcare.services.mappers.ProductMapper;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product dtoToProduct(ProductDTO productDTO) {
        Product product = new Product();

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
    public ProductDTO productToDto(Product product) {
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
