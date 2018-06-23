package pl.javastyle.fitcare.services;

import org.junit.Before;
import org.junit.Test;
import pl.javastyle.fitcare.rest.dto.ProductDTO;
import pl.javastyle.fitcare.services.interfaces.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceImplTest {

    private ProductService productService;

    private List<ProductDTO> products;


    @Before
    public void setup() {
        productService = new ProductServiceImpl(null);

        ProductDTO firstProduct = new ProductDTO();
        firstProduct.setId(1L);
        firstProduct.setName("Banan");
        firstProduct.setCategory("Owoce");
        firstProduct.setCalories(200D);

        ProductDTO secondProduct = new ProductDTO();
        secondProduct.setId(2L);
        secondProduct.setName("Kasza Gryczana");
        secondProduct.setCategory("Mąka, kasze, makarony");
        secondProduct.setCalories(455D);

        ProductDTO thirdProduct = new ProductDTO();
        thirdProduct.setId(3L);
        thirdProduct.setName("Coca Cola");
        thirdProduct.setCategory("Napoje");
        thirdProduct.setCalories(600D);

        ProductDTO fourthProduct = new ProductDTO();
        fourthProduct.setId(4L);
        fourthProduct.setName("Taco");
        fourthProduct.setCategory("Gotowe posiłki");
        fourthProduct.setCalories(150D);

        products = Arrays.asList(
                firstProduct,
                secondProduct,
                thirdProduct,
                fourthProduct);
    }

    @Test
    public void shouldReturnSortedProductsByCategory() {
        List<ProductDTO> sortedProducts = productService.sortAllProductsByCategory(products);

        assertEquals(Long.valueOf(4), sortedProducts.get(0).getId());
        assertEquals(Long.valueOf(2), sortedProducts.get(1).getId());
        assertEquals(Long.valueOf(3), sortedProducts.get(2).getId());
        assertEquals(Long.valueOf(1), sortedProducts.get(3).getId());
    }

    @Test
    public void shouldReturnSortedProductsByName() {
        List<ProductDTO> sortedProducts = productService.sortAllProductsByName(products);

        assertEquals(Long.valueOf(1), sortedProducts.get(0).getId());
        assertEquals(Long.valueOf(3), sortedProducts.get(1).getId());
        assertEquals(Long.valueOf(2), sortedProducts.get(2).getId());
        assertEquals(Long.valueOf(4), sortedProducts.get(3).getId());
    }

    @Test
    public void shouldReturnSortedProductsByCalories() {
        List<ProductDTO> sortedProducts = productService.sortAllProductsByCalories(products);

        assertEquals(Long.valueOf(3), sortedProducts.get(0).getId());
        assertEquals(Long.valueOf(2), sortedProducts.get(1).getId());
        assertEquals(Long.valueOf(1), sortedProducts.get(2).getId());
        assertEquals(Long.valueOf(4), sortedProducts.get(3).getId());
    }
}