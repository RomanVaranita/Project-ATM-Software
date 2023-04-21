import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AtmOperationImplTest {

    private AtmOperationImpl atmOperation;

    @Test
    void depositAmount() {
        double initialBalance = atmOperation.atm.getBalance();
        List<String> initialHistory = atmOperation.history;

        atmOperation.depositAmount(100);

        double expectedBalance = initialBalance + 100;
        List<String> expectedHistory = List.of("Amount Deposited: 100.0$");

        assertEquals(expectedBalance, atmOperation.atm.getBalance());
        assertEquals(expectedHistory, atmOperation.history);
    }

    @Test
    void viewMiniStatement() {
        atmOperation.history.add("Amount Withdrawn: 100.0");
        atmOperation.history.add("Amount Deposited: 200.0$");

        atmOperation.viewMiniStatement();
        //that the output matches the expected value
    }
}

