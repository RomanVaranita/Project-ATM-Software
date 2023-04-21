import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AtmOperationImpl extends Atm_Interface {

    private static final int MAX_WITHDRAWALS_PER_DAY = 3;
    private final ATM atm = new ATM();
    private final List<String> history = new ArrayList<>();
    private int withdrawalsToday = 0;

    @Override
    public void viewBalance() {
        System.out.println("\033[1m" + "Available Balance is: "
                + "\033[0m" + "\033[32m" + atm.getBalance() + "\033[0m" + " $");
    }

    @Override
    public void takeAmount(double takeAmount) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter your ATM PIN to proceed with the withdrawal: ");
        int enteredPin;
        try {
            enteredPin = Integer.parseInt(reader.readLine());
            if (enteredPin == Main.ATM_PIN) {
                if (takeAmount <= atm.getBalance() && withdrawalsToday < MAX_WITHDRAWALS_PER_DAY) {
                    history.add("Amount Withdrawn: " + takeAmount);
                    System.out.println("Collect the Cash " + takeAmount);
                    atm.setBalance(atm.getBalance() - takeAmount);
                    viewBalance();
                    withdrawalsToday++;
                } else if (takeAmount > atm.getBalance()) {
                    System.out.println("Insufficient Balance");
                } else {
                    System.out.println("Maximum withdrawals reached !!");
                }
            } else {
                System.out.println("Incorrect PIN. Withdrawal canceled.");
            }
        } catch (IOException e) {
            System.out.println("\nAn error occurred while reading user input.");
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input. Please enter a number.");
        }
    }

    @Override
    public void depositAmount(double depositAmount) {
        history.add("Amount Deposited: " + depositAmount + "$");
        System.out.println(depositAmount + " Deposited Successfully !! " +"$");
        atm.setBalance(atm.getBalance() + depositAmount);
        viewBalance();
    }

    @Override
    public void viewMiniStatement() {
        for (String transaction : history) {
            System.out.println(transaction);
        }
    }

}
