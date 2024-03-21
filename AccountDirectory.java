import java.time.LocalDateTime;

import ajp.SimpleIo;

public class AccountDirectory {

    public static void main(String[] args) {
        Account[] accounts = new Account[10];
        int numberOfAccounts = 0;
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

                SimpleIo.prompt("\nCommands: 3=deposit, 4=withdraw, 5=close, 6=back, 0=exit\n");
                SimpleIo.prompt("Enter command: ");
                command = SimpleIo.readLine().trim();
            }

            switch (command) {
                case "0":
                    System.out.println("Bye Bye!!!");
                    return;
                case "1":
                    System.out.println("*** Create Account ***");
                    SimpleIo.prompt("Enter your name: ");
                    String name = SimpleIo.readLine().trim();
                    SimpleIo.prompt("Enter your Social Security Number: ");
                    String ssn = SimpleIo.readLine().trim();
                    SimpleIo.prompt("Enter your age: ");
                    int age = Integer.valueOf(SimpleIo.readLine().trim());
                    SimpleIo.prompt("Enter the amount: ");
                    String inputAmount = SimpleIo.readLine().trim();
                    double amount = Double.parseDouble(inputAmount);
                    accounts[numberOfAccounts] = new Account.Builder(
                            name,
                            ssn,
                            age,
                            amount,
                            Account.Type.CHECKING)
                            .email("al@gmail.com")
                            .build();
                    numberOfAccounts++;
                    break;
                case "2":
                    System.out.println("*** Accounts ***");
                    for (int i = 0; i < numberOfAccounts; i++) {
                        System.out.println(i + " - " + accounts[i].name);
                    }
                    SimpleIo.prompt("Select an account: ");
                    accountSelected = Integer.valueOf(SimpleIo.readLine().trim());
                    break;
                case "3":
                    System.out.println("*** Withdraw Money ***");
                    break;
                case "4":
                    System.out.println("*** Close Account ***");
                    break;
                case "6":
                    accountSelected = -1;
                    break;

                default:
                    SimpleIo.prompt("Commands: 1=create, 2=deposit, 3=withdraw, 4=close, 0=exit\n");
                    break;
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

    public double deposit(double amount) {
        balance += amount;
        return balance;
    }

    public double withdraw(double amount) {
        balance -= amount;
        return balance;
    }

    public void close() {
        balance = 0.0;
        status = Status.CLOSED;
    }

    public static class Builder {
        // Required fields
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

        public Builder(String name, String ssn, int age, double initialBalance, Type type) {
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