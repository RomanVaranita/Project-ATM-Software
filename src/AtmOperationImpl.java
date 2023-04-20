import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AtmOperationImpl extends Atm_Interface {

    private static final int MAX_WITHDRAWALS_PER_DAY = 3;
    private static final int INACTIVITY_TIMEOUT_SECONDS = 50;

    private ATM atm = new ATM();
    private List<String> history = new ArrayList<>();
    private int withdrawalsToday = 0;
    private Instant lastActivityTime;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void viewBalance() {
        checkActivityTimeout();
        System.out.println("Available Balance is : " + atm.getBalance());
    }

    @Override
    public void takeAmount(double takeAmount) {
        checkActivityTimeout();
        if (takeAmount <= atm.getBalance() && withdrawalsToday < MAX_WITHDRAWALS_PER_DAY) {
            history.add("Amount Withdrawn: " + takeAmount);
            System.out.println("Collect the Cash " + takeAmount);
            atm.setBalance(atm.getBalance() - takeAmount);
            viewBalance();
            withdrawalsToday++;
        } else {
            System.out.println("Insufficient Balance or Maximum withdrawals reached !!");
        }
    }

    @Override
    public void depositAmount(double depositAmount) {
        checkActivityTimeout();
        history.add("Amount Deposited: " + depositAmount);
        System.out.println(depositAmount + " Deposited Successfully !! ");
        atm.setBalance(atm.getBalance() + depositAmount);
        viewBalance();
    }

    @Override
    public void viewMiniStatement() {
        checkActivityTimeout();
        for (String transaction : history) {
            System.out.println(transaction);
        }
    }

    private void checkActivityTimeout() {
        if (lastActivityTime != null && Instant.now().minusSeconds(INACTIVITY_TIMEOUT_SECONDS).isAfter(lastActivityTime)) {
            System.out.println("Logging out due to inactivity...");
            System.exit(0);
        }
        lastActivityTime = Instant.now();
    }
}
