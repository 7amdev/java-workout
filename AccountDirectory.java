import java.time.LocalDateTime;

import ajp.SimpleIo;

public class AccountDirectory {
    private static final int DEFAULT_INITIAL_CAPACITY = 2;
    private static int numberOfAccounts = 0;
    private static Account[] accounts = new Account[DEFAULT_INITIAL_CAPACITY];

    private AccountDirectory() {
    }

    private static void ensureCapacity() {
        if (accounts.length == numberOfAccounts) {
            Account[] copy = new Account[2 * accounts.length + 1];
            System.arraycopy(accounts, 0, copy, 0, accounts.length);
            accounts = copy;
            copy = null;
        }
    }

    private static void push(Account a) {
        ensureCapacity();
        accounts[numberOfAccounts] = a;
        numberOfAccounts += 1;
    }

    private static Account pull(int accountNumber) {
        int i = 0;
        for (; i < numberOfAccounts; i++) {
            if (accounts[i].id == accountNumber)
                break;
        }

        Account result = accounts[i];
        accounts[i] = accounts[numberOfAccounts - 1];
        numberOfAccounts -= 1;

        // Null the obsolete reference and avoids memory leak
        accounts[numberOfAccounts] = null;

        return result;
    }

    public static void main(String[] args) {
        int accountSelected = -1;
        String command;

        while (true) {
            // clear the terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if (accountSelected == -1) {
                SimpleIo.prompt("Commands: 1=create, 2=select, 0=exit\n");
                SimpleIo.prompt("Enter command: ");
                command = SimpleIo.readLine().trim();
            } else {
                SimpleIo.prompt(
                        "Account: " + accounts[accountSelected].name + "\n" +
                                "    Age: " + accounts[accountSelected].age + "\n" +
                                "    Balance: " + accounts[accountSelected].balance + "\n");

                SimpleIo.prompt("\nCommands: 3=deposit, 4=withdraw, 5=close, 6=archive, 7=back, 0=exit\n");
                SimpleIo.prompt("Enter command: ");
                command = SimpleIo.readLine().trim();
            }

            switch (command) {
                case "0": {
                    System.out.println("Bye Bye!!!");
                    return;
                }
                case "1": {
                    System.out.println("*** Create Account(" + accounts.length + ") ***");
                    SimpleIo.prompt("Enter your name: ");
                    String name = SimpleIo.readLine().trim();
                    SimpleIo.prompt("Enter your Social Security Number: ");
                    String ssn = SimpleIo.readLine().trim();
                    SimpleIo.prompt("Enter your age: ");
                    int age = Integer.valueOf(SimpleIo.readLine().trim());
                    SimpleIo.prompt("Enter the amount: ");
                    String inputAmount = SimpleIo.readLine().trim();
                    double amount = Double.parseDouble(inputAmount);

                    Account newAccount = new Account.Builder(numberOfAccounts, name, ssn, age, amount,
                            Account.Type.CHECKING).build();
                    AccountDirectory.push(newAccount);

                    break;
                }
                case "2": {
                    System.out.println("*** Accounts ***");
                    for (int i = 0; i < numberOfAccounts; i++) {
                        System.out.println(i + " - " + accounts[i].name);
                    }
                    SimpleIo.prompt("Select an account: ");
                    accountSelected = Integer.valueOf(SimpleIo.readLine().trim());
                    break;
                }
                case "3": {
                    System.out.println("*** Deposit Money ***");
                    SimpleIo.prompt("Enter the amount: ");
                    String value = SimpleIo.readLine().trim();
                    double amount = Double.parseDouble(value);
                    if (accountSelected != -1 && amount > 0) {
                        accounts[accountSelected].deposit(amount);
                    }
                    break;
                }
                case "4": {
                    System.out.println("*** Withdraw Account ***");
                    SimpleIo.prompt("Enter the amount: ");
                    String value = SimpleIo.readLine().trim();
                    double amount = Double.parseDouble(value);
                    if (accountSelected != -1 && amount > 0) {
                        accounts[accountSelected].withdraw(amount);
                    }
                    break;
                }
                case "7": {
                    accountSelected = -1;
                    break;
                }
                default: {
                    SimpleIo.prompt("Commands: 1=create, 2=deposit, 3=withdraw, 4=close, 0=exit\n");
                    break;
                }
            }
        }

    }
}

class Account {
    public static enum Type {
        CHECKING,
        DEBIT
    };

    public static enum Status {
        OPEN,
        CLOSED
    }

    // Instance Fields
    // Required fields
    int id;
    String name;
    String ssn;
    int age;
    double balance;
    Type type;
    final LocalDateTime createdAt;

    // Optional fields
    String email;
    String birthdate;
    Status status;

    private Account(Builder builder) {
        id = builder.id;
        name = builder.name;
        ssn = builder.ssn;
        age = builder.age;
        balance = builder.balance;
        type = builder.type;
        email = builder.email;
        birthdate = builder.birthdate;
        status = builder.status;
        createdAt = builder.createdAt;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if ((balance - amount) < 0)
            return;

        balance -= amount;
    }

    public void close() {
        balance = 0.0;
        status = Status.CLOSED;
    }

    public static class Builder {
        // Required fields
        private final int id;
        private final String name;
        private final String ssn;
        private final int age;
        private final double balance;
        private final Type type;

        // Optional fields
        private String email = "";
        private String birthdate = "";
        private Status status = Status.OPEN;
        private final LocalDateTime createdAt = LocalDateTime.now();

        public Builder(int id, String name, String ssn, int age, double initialBalance, Type type) {
            this.id = id;
            this.name = name;
            this.ssn = ssn;
            this.age = age;
            this.balance = initialBalance;
            this.type = type;
        }

        public Builder email(String val) {
            this.email = val;
            return this;
        }

        public Builder birthdate(String val) {
            this.birthdate = val;
            return this;
        }

        public Builder status(Status val) {
            this.status = val;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

}