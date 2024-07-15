import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private double balance;
    private double lastTransaction;

    public Account() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount != 0) {
            balance += amount;
            lastTransaction = amount;
        }
    }

    public void withdraw(double amount) {
        if (amount != 0 && amount <= balance) {
            balance -= amount;
            lastTransaction = -amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void getBalance() {
        System.out.println("Current balance: $" + balance);
    }

    public void getLastTransaction() {
        if (lastTransaction > 0) {
            System.out.println("Deposited: $" + lastTransaction);
        } else if (lastTransaction < 0) {
            System.out.println("Withdraw: $" + Math.abs(lastTransaction));
        } else {
            System.out.println("No transaction occurred.");
        }
    }
}

public class ATMSystem {
    private static String atmName = "CUB"; 
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to " + atmName); 
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please enter again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter a new account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Create a PIN: ");
        String pin = scanner.nextLine();
        String accountId = accountNumber + "-" + pin;

        if (accounts.containsKey(accountId)) {
            System.out.println("Account already exists.");
        } else {
            accounts.put(accountId, new Account());
            System.out.println("Account created successfully. Please remember your account number and PIN.");
        }
    }

    private static void login() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();
        String accountId = accountNumber + "-" + pin;

        Account account = accounts.get(accountId);
        if (account != null) {
            System.out.println("Login successful.");
            operateAccount(account);
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    private static void operateAccount(Account account) {
        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Last Transaction");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    account.getBalance();
                    break;
                case 2:
                    System.out.print("Enter an amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter an amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    account.getLastTransaction();
                    break;
                case 5:
                    System.out.println("Logged out successfully.");
                    return; 
                default:
                    System.out.println("Invalid option! Please enter again.");
            }
        }
    }
}
