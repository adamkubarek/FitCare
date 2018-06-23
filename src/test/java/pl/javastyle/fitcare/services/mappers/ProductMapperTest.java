package pl.javastyle.fitcare.services.mappers;

import org.junit.Before;
import org.junit.Test;
import pl.javastyle.fitcare.domain.Category;
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
        productDTO.setCarbs(70D);
        productDTO.setProtein(10D);
        productDTO.setFat(10D);
        productDTO.setCalories(410D);
    }

    private void fillProductFields() {
        product = new Product();
        product.setId(1L);
        product.setName("Banan");
        Category category = new Category();
        category.setName("Owoce");
        product.setCategory(category);
        product.setCarbs(15D);
        product.setProtein(5D);
        product.setFat(10D);
        product.setCalories(170D);
    }

    @Test
    public void dtoProductShouldHaveTheSameFieldsAsProduct() {
        Product result = mapper.dtoToDomain(productDTO);

        assertEquals(productDTO.getId(), result.getId());
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getCategory(), result.getCategory().getName());
        assertEquals(productDTO.getCarbs(), result.getCarbs(), 0.001);
        assertEquals(productDTO.getProtein(), result.getProtein(), 0.001);
        assertEquals(productDTO.getFat(), result.getFat(), 0.001);
        assertEquals(productDTO.getCalories(), result.getCalories(), 0.001);
    }

    @Test
    public void productShouldHaveTheSameFieldsAsDtoProduct() {
        ProductDTO result = mapper.domainToDto(product);

        assertEquals(product.getCategory().getName(), result.getCategory());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getCarbs(), result.getCarbs(), 0.001);
        assertEquals(product.getProtein(), result.getProtein(), 0.001);
        assertEquals(product.getFat(), result.getFat(), 0.001);
        assertEquals(product.getCalories(), result.getCalories(), 0.001);
    }
}