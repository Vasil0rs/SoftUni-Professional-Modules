package p01_Database;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    @Test
    public void testConstructionHasToCreativeObject() throws OperationNotSupportedException {
        Integer[] numbers = {5, 4, 8, 14};
        Database database = new Database(numbers);
        int length = database.getElements().length;
        Integer[] databaseNumbers = database.getElements();

        Assert.assertEquals("count of elements is incorrect", 4, length);
        for (int i = 0; i < databaseNumbers.length; i++) {
            Assert.assertEquals(numbers[i], databaseNumbers[i]);
        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWhenTestMoreThenSixteenElements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17];
        new Database(numbers);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWhenThrowLesThenOneElement() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowException() throws OperationNotSupportedException {
        Integer[] numbers = {5, 4, 8, 14};
        Database database = new Database(numbers);
        database.add(null);
    }

    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        Integer[] numbers = {5, 4, 8, 14};
        Database database = new Database(numbers);
        database.add(17);
        Assert.assertEquals(5, database.getElements().length);
        Assert.assertEquals(Integer.valueOf(17), database.getElements()[4]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowExWhitEmptyData() throws OperationNotSupportedException {
        Integer[] numbers = {};
        Database database = new Database(numbers);
        database.remove();
    }
    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        Integer[] numbers = {5, 4, 8, 14};
        Database database = new Database(numbers);
        database.remove();
        Assert.assertEquals(3,database.getElements().length);
    }
}
