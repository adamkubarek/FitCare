package pl.javastyle.fitcare.product;

import org.springframework.stereotype.Component;
import pl.javastyle.fitcare.core.Mapper;
import pl.javastyle.fitcare.category.Category;
import pl.javastyle.fitcare.user.User;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {

    @Override
    public Product dtoToDomain(ProductDTO productDTO, User user) {
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCalories(productDTO.getCalories());
        product.setMacronutrients(productDTO.getMacronutrients());
        product.setUser(user);

        Category category = new Category();
        category.setName(productDTO.getCategory());
        category.setUser(user);
        product.setCategory(category);

        return product;
    }

    @Override
    public ProductDTO domainToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCalories(product.getCalories());
        productDTO.setMacronutrients(product.getMacronutrients());
        productDTO.setCategory(product.getCategory().getName());

        return productDTO;
    }
}
