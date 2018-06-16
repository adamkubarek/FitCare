package pl.javastyle.fitcare.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    private Product product;

    @Before
    public void setup () {
        this.product = new Product();
    }

    @Test
    public void shouldBePersisted() {
        this.product.setId(1L);

        assertTrue(this.product.isPersisted());
    }

    @Test
    public void shouldNotBePersisted() {
        assertFalse(this.product.isPersisted());
    }
}