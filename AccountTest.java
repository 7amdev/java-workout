import ajp.SimpleIo;

public class AccountTest {
    public static void main(String args[]) {
        // Account[] accounts = new Account[10];
        // while (true) {
        // SimpleIo.prompt("1-CreateAccount 2-Deposit 2-Widraw 3-Balance 4-close
        // 0-exit");
        // String userInput = SimpleIo.readLine().trim();
        // if (userInput == "0") {
        // break;
        // }

        // if (userInput == "1") {
        // SimpleIo.prompt("Enter your name: ");
        // String name = SimpleIo.readLine().trim();
        // SimpleIo.prompt("Enter the deposit amount: ");
        // double amount = Double.parseDouble(SimpleIo.readLine().trim());
        // accounts[Account.getaccountsTotal()] = new Account(amount, name);

        // }

        // }
        Account account_1 = new Account("John Doe", 1000.0);
        Account account_2 = new Account("Jane Doe", 400.0);

        System.out.println("Accounts Total: " + Account.getaccountsTotal());

        account_1.withdraw(250);
        account_1.deposit(50);

        System.out.println("Account #1 balance is: " + account_1.getBalance());

        account_1.withdraw(200);
        account_1.close();
        System.out.println("Account #1 balance is: " + account_1.getBalance());

        account_2.deposit(320);
        account_2.deposit(500);
        System.out.println("Account #2 balance is: " + account_2.getBalance());

        System.out.println("Balance Total: " + Account.getBalanceTotal());
    }
}
