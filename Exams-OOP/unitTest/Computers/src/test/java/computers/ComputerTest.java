package computers;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {
    private Computer computer;

    @Before
    public void seUp(){
       this.computer = new Computer("name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWhitNullAsSame(){
        new Computer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWhitIsEmptyAsSame(){
        new Computer("");
    }

    @Test
    public void testConstructorShouldSetCorrectName(){
        assertEquals("name",this.computer.getName());
    }

    @Test
    public void testGetCorrectPartsShouldReturnCollection(){
      assertNotNull(this.computer.getParts());
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testGetCorrectPartsShouldReturnUnmodifiableCollection(){
        this.computer.getParts().add(new Part("name",13));
    }
    @Test
    public void getTotalPriceShouldReturnNotEmpty(){
        this.computer.addPart(new Part("name",12.00));
        double price = this.computer.getTotalPrice();
        assertEquals(12.00,price,0.00);
    }
    @Test
    public void testTotalPriceShouldReturnZero(){
        double price = this.computer.getTotalPrice();
        assertEquals(0.00,price,0.00);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddPartShouldReturnNull(){
        this.computer.addPart(null);
    }
    @Test
    public void testAddPartShouldAddPart(){
        this.computer.addPart(new Part("name",12.00));
        assertNotNull(this.computer.getPart("name"));
    }
    @Test
    public void testRemovePartShouldRemovePart(){
        Part part = new Part("name", 12.00);
        assertFalse(this.computer.removePart(part));
        this.computer.addPart(part);
        assertTrue(this.computer.removePart(part));
    }
    @Test
    public void testGetPartShouldReturnNull(){
        assertNull(this.computer.getPart("name"));
    }
    @Test
    public void testGetPartShouldReturnCorrectPart(){
        Part part = new Part("name", 12.00);
        this.computer.addPart(part);
        assertNotNull(this.computer.getPart("name"));

    }
}