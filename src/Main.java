import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static final int ATM_NUMBER = 1234;
    public static int ATM_PIN = 1989;

    public static void main(String[] args) {
        AtmOperationImpl op = new AtmOperationImpl();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("\033[1;34m╔══════════════════════════════════════════╗");
            System.out.println("║        Welcome to the ATM Machine!        ║");
            System.out.println("╚══════════════════════════════════════════╝\033[0m");

            System.out.println("\033[34m\nPlease enter your credentials:\033[0m");
            System.out.print("\033[34mATM number: \033[0m");
            int atmNumber = Integer.parseInt(reader.readLine());
            System.out.print("\033[34mPIN:        \033[0m");
            int pin = Integer.parseInt(reader.readLine());

            boolean isAuthenticated = (atmNumber == ATM_NUMBER) && (pin == ATM_PIN);
            if (isAuthenticated) {
                int choice;
                do {
                    System.out.println("\033[34m\n╔══════════════════════════════════════════╗");
                    System.out.println("║        \033[32m1. View Available Balance\033[34m         ║");
                    System.out.println("║        \033[33m2. Withdraw Amount\033[34m                ║");
                    System.out.println("║        \033[33m3. Deposit Amount\033[34m                 ║");
                    System.out.println("║        \033[32m4. View Transaction History\033[34m       ║");
                    System.out.println("║        \033[35m5. Change PIN\033[34m                     ║");
                    System.out.println("║        \033[31m6. Exit\033[34m                           ║");
                    System.out.println("╚══════════════════════════════════════════╝\033[0m");
                    System.out.print("\nPlease enter your choice: ");
                    choice = Integer.parseInt(reader.readLine());

                    switch (choice) {
                        case 1 -> op.viewBalance();
                        case 2 -> {
                            System.out.print("\nPlease enter the amount to withdraw: ");
                            double takeAmount = Double.parseDouble(reader.readLine());
                            op.takeAmount(takeAmount);
                        }
                        case 3 -> {
                            System.out.print("\nPlease enter the amount to deposit: ");
                            double depositAmount = Double.parseDouble(reader.readLine());
                            op.depositAmount(depositAmount);
                        }
                        case 4 -> op.viewMiniStatement();
                        case 5 -> { // Added option for changing PIN
                            System.out.print("\nPlease enter your current PIN: ");
                            int currentPin = Integer.parseInt(reader.readLine());
                            if (currentPin == ATM_PIN) {
                                System.out.print("Please enter a new PIN: ");
                                ATM_PIN = Integer.parseInt(reader.readLine());
                                System.out.println("\nPIN successfully changed.");
                            } else {
                                System.out.println("\nIncorrect PIN. Please try again.");
                            }
                        }
                        default -> System.out.println("\nInvalid choice. Please try again.");
                        case 6 -> System.out.println("\nThank you for using the ATM. Goodbye!");
                    }
                } while (choice != 6);
            } else {
                System.out.println("\nInvalid ATM number or PIN.");
            }

            System.out.println("\nValidation complete.");
        } catch (IOException e) {
            System.out.println("\nAn error occurred while reading user input.");
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input. Please enter a number.");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("\nAn error occurred while closing the input stream.");
            }
        }
    }
}
