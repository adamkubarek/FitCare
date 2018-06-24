package pl.javastyle.fitcare.services.mappers;

import org.junit.Before;
import org.junit.Test;
import pl.javastyle.fitcare.domain.Category;
import pl.javastyle.fitcare.domain.Macronutrients;
import pl.javastyle.fitcare.domain.Product;
import pl.javastyle.fitcare.rest.dto.ProductDTO;

import static org.junit.Assert.assertEquals;

public class ProductMapperTest {

    private Product product;
    private ProductDTO productDTO;

    private Mapper<Product, ProductDTO> mapper;

    @Before
    public void setup() {
        fillProductFields();
        fillProductDtoFields();

        mapper = new ProductMapper();
    }

    private void fillProductDtoFields() {
        productDTO = new ProductDTO();
        productDTO.setId(2L);
        productDTO.setName("Ryż");
        productDTO.setCategory("Kasze i ryże");
        productDTO.setMacronutrients(new Macronutrients(10D,70D,10D));
        productDTO.setCalories(410D);
    }

    private void fillProductFields() {
        product = new Product();
        product.setId(1L);
        product.setName("Banan");
        Category category = new Category();
        category.setName("Owoce");
        product.setCategory(category);
        product.setMacronutrients(new Macronutrients(5D,15D,10D));
        product.setCalories(170D);
    }

    @Test
    public void dtoProductShouldHaveTheSameFieldsAsProductAfterMapping() {
        Product result = mapper.dtoToDomain(productDTO);

        assertEquals(productDTO.getId(), result.getId());
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getCategory(), result.getCategory().getName());
        assertEquals(productDTO.getMacronutrients(), result.getMacronutrients());
        assertEquals(productDTO.getCalories(), result.getCalories(), 0.001);
    }

    @Test
    public void productShouldHaveTheSameFieldsAsDtoProductAfterMapping() {
        ProductDTO result = mapper.domainToDto(product);

        assertEquals(product.getCategory().getName(), result.getCategory());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getMacronutrients(), result.getMacronutrients());
        assertEquals(product.getCalories(), result.getCalories(), 0.001);
    }
}