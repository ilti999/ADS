import java.util.*;

class BankAccount {
    int accountNumber;
    String username;
    double balance;

    public BankAccount(int accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
}

public class Assignment2 {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<BankAccount> accountRequests = new LinkedList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        BankAccount[] arr = {
                new BankAccount(1, "Ali", 150000),
                new BankAccount(2, "Sara", 220000),
                new BankAccount(3, "John", 100000)
        };

        System.out.println("Predefined Accounts:");
        for (BankAccount a : arr) {
            System.out.println(a.username + " - " + a.balance);
        }

        while (true) {
            System.out.println("\n1-Bank  2-ATM  3-Admin  4-Exit");
            int choice = sc.nextInt();

            if (choice == 1) bankMenu();
            else if (choice == 2) atmMenu();
            else if (choice == 3) adminMenu();
            else break;
        }
    }

    static void bankMenu() {
        System.out.println("\n1-Add Account  2-Deposit  3-Withdraw  4-Back");
        int ch = sc.nextInt();

        if (ch == 1) {
            System.out.print("Enter name: ");
            String name = sc.next();
            accountRequests.add(new BankAccount(0, name, 0));
            System.out.println("Request added");
        }

        else if (ch == 2) {
            System.out.print("Enter name: ");
            String name = sc.next();
            BankAccount acc = find(name);
            if (acc != null) {
                System.out.print("Deposit: ");
                double d = sc.nextDouble();
                acc.balance += d;
                history.push("Deposit " + d + " to " + name);
                System.out.println("New balance: " + acc.balance);
            }
        }

        else if (ch == 3) {
            System.out.print("Enter name: ");
            String name = sc.next();
            BankAccount acc = find(name);
            if (acc != null) {
                System.out.print("Withdraw: ");
                double w = sc.nextDouble();
                acc.balance -= w;
                history.push("Withdraw " + w + " from " + name);
                System.out.println("New balance: " + acc.balance);
            }
        }
    }

    static void atmMenu() {
        System.out.print("Enter name: ");
        String name = sc.next();
        BankAccount acc = find(name);

        if (acc == null) return;

        System.out.println("1-Balance 2-Withdraw");
        int ch = sc.nextInt();

        if (ch == 1) {
            System.out.println("Balance: " + acc.balance);
        } else {
            System.out.print("Withdraw: ");
            double w = sc.nextDouble();
            acc.balance -= w;
            history.push("ATM Withdraw " + w);
        }
    }

    static void adminMenu() {
        System.out.println("\n1-Process Account 2-Bills 3-History 4-Back");
        int ch = sc.nextInt();

        if (ch == 1) {
            if (!accountRequests.isEmpty()) {
                BankAccount acc = accountRequests.poll();
                acc.accountNumber = accounts.size() + 1;
                accounts.add(acc);
                System.out.println("Account created: " + acc.username);
            }
        }

        else if (ch == 2) {
            System.out.println("1-Add Bill 2-Process Bill");
            int b = sc.nextInt();

            if (b == 1) {
                System.out.print("Bill name: ");
                String bill = sc.next();
                billQueue.add(bill);
            } else {
                if (!billQueue.isEmpty()) {
                    System.out.println("Processing: " + billQueue.poll());
                }
            }
        }

        else if (ch == 3) {
            if (!history.isEmpty()) {
                System.out.println("Last: " + history.peek());
                history.pop();
                System.out.println("Undo done");
            }
        }
    }

    static BankAccount find(String name) {
        for (BankAccount a : accounts) {
            if (a.username.equals(name)) return a;
        }
        System.out.println("Not found");
        return null;
    }
}