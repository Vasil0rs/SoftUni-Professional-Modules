package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private static final Person[] PEOPLE = {new Person(1, "first"),
            new Person(2, "second"), new Person(2, "third")};

    @Before
    public void repairDatabase() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    @Test
    public void testConstructionHasToCreativeObject() {
        int length = database.getElements().length;
        Person[] databaseNumbers = database.getElements();

        Assert.assertEquals("count of elements is incorrect", 3, length);
        for (int i = 0; i < databaseNumbers.length; i++) {
            Assert.assertEquals(PEOPLE[i], databaseNumbers[i]);
        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWhenTestMoreThenSixteenElements() throws OperationNotSupportedException {
        Person[] person = new Person[17];
        new Database(person);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWhenThrowLesThenOneElement() throws OperationNotSupportedException {
        Person[] person = new Person[0];
        new Database(person);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowException() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        database.add(new Person(4, "fourth"));
        Assert.assertEquals(4, database.getElements().length);
        Assert.assertEquals(4, database.getElements()[3].getId());
        Assert.assertEquals("fourth", database.getElements()[3].getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowExWhitEmptyData() throws OperationNotSupportedException {
        Person[] person = {};
        Database database = new Database(person);
        database.remove();
    }

    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Person[] elements = database.getElements();
        Assert.assertEquals(2, elements[elements.length - 1].getId());
        Assert.assertEquals("second", elements[elements.length - 1].getUsername());

        for (int i = 0; i < elements.length; i++) {
            Assert.assertEquals(elements[i], PEOPLE[i]);
        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernamesIfIsNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernamesThrowEmptyData() throws OperationNotSupportedException {
        database = new Database(null, null, null);
        database.findByUsername("first");
    }

    @Test
    public void testFindByUsernameReturnCorrectPerson() throws OperationNotSupportedException {
        Person person = database.findByUsername("first");
        Assert.assertEquals(1, person.getId());
        Assert.assertEquals("first", person.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameIfCantFindName() throws OperationNotSupportedException {
        database = new Database();
        database.findByUsername("first");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdIfCantFindName() throws OperationNotSupportedException {
        database = new Database();
        database.findById(1);
    }

    @Test
    public void testFindByIdReturnCorrectPerson() throws OperationNotSupportedException {
        Person person = database.findById(1);
        Assert.assertEquals(1, person.getId());
        Assert.assertEquals("first", person.getUsername());

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdThrowEmptyData() throws OperationNotSupportedException {
        database = new Database(null, null, null);
        database.findById(1);
    }
}