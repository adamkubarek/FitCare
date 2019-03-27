package pl.javastyle.fitcare.domain;

import org.junit.Before;
import org.junit.Test;
import pl.javastyle.fitcare.product.Product;

import static org.junit.Assert.*;

public class ProductTest {

    private Product product;

    @Before
    public void setup () {
        this.product = new Product();
    }

    @Test
    public void shouldBePersistedWhenIdIsSet() {
        this.product.setId(1L);

        assertTrue(this.product.isPersisted());
    }

    @Test
    public void shouldNotBePersistedWhenIdIsNotSet() {
        this.product.setId(null);

        assertFalse(this.product.isPersisted());
    }
}