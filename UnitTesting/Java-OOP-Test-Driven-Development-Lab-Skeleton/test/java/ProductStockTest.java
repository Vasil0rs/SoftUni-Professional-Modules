import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductStockTest {
    private ProductStock stock;

    @Before
    public void setUp() {
        stock = new Instock();
    }

    @Test
    public void testCountShouldReturnCorrectSize() {
        assertEquals(0, stock.getCount());
        stock.add(new Product());
        assertEquals(1, stock.getCount());
    }
    @Test
    public void  testContainsShouldReturnCorrectBooleanForAllClasses(){
        Product product =new Product("test label",3.00,1);
        assertFalse(stock.contains(product));

        stock.add(product);

        assertTrue(stock.contains(product));

        product.setLabel("not_pres ent_label");
        assertFalse(stock.contains(product));

    }

}