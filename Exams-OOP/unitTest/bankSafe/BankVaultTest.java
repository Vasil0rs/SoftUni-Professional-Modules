package bankSafe;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javax.naming.OperationNotSupportedException;

public class BankVaultTest {
    @Test
    public void TestConstructor() {
        BankVault bank = new BankVault();
        assertNotNull(bank.getVaultCells());
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestAddIthemWithInvalidCell() throws OperationNotSupportedException {
        BankVault bank = new BankVault();
        Item ithem=new Item("Pesho","100");
        bank.addItem("abc",ithem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestAddIthemWithInvalidCell2() throws OperationNotSupportedException {
        BankVault bank = new BankVault();
        Item ithem=new Item("Pesho","100");
        bank.addItem("A1",ithem);
        bank.addItem("A1",ithem);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void TestAddIthemWithInvalidCell3() throws OperationNotSupportedException {
        BankVault bank = new BankVault();
        Item ithem=new Item("Pesho","100");
        bank.addItem("A1",ithem);
        bank.addItem("A2",ithem);
    }

    @Test
    public void TestAddIthemWithValidCell() throws OperationNotSupportedException {
        BankVault bank = new BankVault();
        Item ithem=new Item("Pesho","100");
        String result= bank.addItem("A1",ithem);
        assertEquals("Item:100 saved successfully!",result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestRemoveIthemWithInvalidCell() throws OperationNotSupportedException {
        BankVault bank = new BankVault();
        Item ithem=new Item("Pesho","100");
        bank.addItem("A2",ithem);
        bank.removeItem("abc",ithem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestRemoveIthemWithInvalidCell2() throws OperationNotSupportedException {
        BankVault bank = new BankVault();
        Item ithem=new Item("Pesho","100");
        bank.addItem("A2",ithem);
        bank.removeItem("A2",ithem);
        bank.removeItem("A2",ithem);
    }

    @Test
    public void TestRemoveIthemWithValidCell() throws OperationNotSupportedException {
        BankVault bank = new BankVault();
        Item ithem=new Item("Pesho","100");
        bank.addItem("A1",ithem);
        String result= bank.removeItem("A1",ithem);
        assertEquals("Remove item:100 successfully!",result);
    }
}