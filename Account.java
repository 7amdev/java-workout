import ajp.SimpleIo;

public class Account {
    // Class Variables
    private static int accountsTotal = 0;
    private static double balanceTotal = 0.0;

    // Class Methods
    public static int getaccountsTotal() {
        return accountsTotal;
    }

    public static double getBalanceTotal() {
        return balanceTotal;
    }

    // Instance Variables
    private String owner;
    private double balance;

    // Constructors
    Account(String name, double initial_balance) {
        owner = name;
        balance = initial_balance;
        accountsTotal += 1;
        balanceTotal += initial_balance;
    }

    Account() {
        balance = 0.0;
    }

    // Instance Methods
    public String getName() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        balanceTotal += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
        balanceTotal -= amount;
    }

    public void close() {
        balanceTotal -= balance;
        balance = 0.0;
        accountsTotal -= 1;
    }

    public static void main(String[] args) {
        Account account = null;
        String errorMessage = "";
        while (true) {
            // clear the terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if (errorMessage != "") {
                SimpleIo.prompt("Error: " + errorMessage + "\n");
                errorMessage = "";
            }

            if (account != null) {
                SimpleIo.prompt("Account name: " + account.getName());
                SimpleIo.prompt(" | Account balance: " + String.valueOf(account.getBalance()));
                SimpleIo.prompt("\n");
            } else {
                SimpleIo.prompt("Account name: <none> | ");
                SimpleIo.prompt("Account balance: 0.0\n");
            }

            SimpleIo.prompt("Commands: 1=create, 2=deposit, 3=withdraw, 4=close, 0=exit\n");
            SimpleIo.prompt("Enter command: ");
            String command = SimpleIo.readLine().trim();

            switch (command) {
                case "0":
                    System.out.println("Bye Bye!!!");
                    return;
                case "1":
                    System.out.println("*** Create Account ***");
                    SimpleIo.prompt("Enter your name: ");
                    String inputName = SimpleIo.readLine().trim();
                    SimpleIo.prompt("Enter the amount: ");
                    String inputAmount = SimpleIo.readLine().trim();
                    double amount = Double.parseDouble(inputAmount);
                    account = new Account(inputName, amount);
                    SimpleIo.prompt("Account name: " + account.getName());
                    break;
                case "2":
                    System.out.println("*** Deposit Money ***");
                    if (account == null) {
                        errorMessage = "Create an account first.";
                        break;
                    }
                    break;
                case "3":
                    System.out.println("*** Withdraw Money ***");
                    break;
                case "4":
                    System.out.println("*** Close Account ***");
                    break;

                default:
                    SimpleIo.prompt("Commands: 1=create, 2=deposit, 3=withdraw, 4=close, 0=exit\n");
                    break;
            }
        }
    }
}
