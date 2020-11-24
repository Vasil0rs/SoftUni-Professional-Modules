package Bank;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountTest {

    @Test
    public void testBankAccountCreationStartWhitZero() {
        BankAccount bankAccount = new BankAccount();
        double balance = bankAccount.getBalance();
        assertEquals(0, balance, 0);
    }
    @Test
    public void testDeposit(){
        BankAccount account = new BankAccount();

        account.deposit(50);

        assertEquals(50, account.getBalance(), 0);

    }
}
