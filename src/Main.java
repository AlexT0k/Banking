import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Main {
    public static class BankAccount {
        public double balance;
        public String name;
        String id;

        public BankAccount(String name, double balance, String id) {
            this.name = name;
            this.balance = balance;
            this.id = id;
        }

        public void deposit(double amount) {
            balance = balance + amount;
        }

        public void withdraw(double amount) {
            if (balance >= amount) {
                balance = balance - amount;
            } else {
                System.out.println("impossible operation");
            }
        }

        public void printBalance() {
            System.out.println(balance);
        }

        public void transfer(double amount, BankAccount targetAccount) {
            if (balance >= amount) {
                this.withdraw(amount);
                targetAccount.deposit(amount);
                try {
                    FileWriter writer = new FileWriter("transactionHistory.txt",true);
                    writer.write(this.name+"->"+targetAccount.name+"("+amount+")\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("impossible operation");
            }
        }

    }

    public static Map<String, BankAccount> readAccounts(String fileName) {
        Map<String, BankAccount> accounts = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 3) {
                    String name = parts[0].trim();
                    double balance = Double.parseDouble(parts[1].trim());
                    String id = parts[2].trim();
                    accounts.put(id, new BankAccount(name, balance, id));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }
        return accounts;
    }


    public static void accountsUpdate(Map<String, BankAccount> accounts, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (BankAccount account : accounts.values()) {
                writer.println(account.name + ", " + account.balance + ", " + account.id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Map<String, BankAccount> accounts = readAccounts("accounts.txt");
        Scanner scanner = new Scanner(System.in);
        String answer;

        do {
            System.out.println("Would you like to transfer any money? y/n");
            answer = scanner.nextLine();

            if (answer.equals("y")) {
                System.out.println("Enter sender account id");
                String sender_id = scanner.nextLine();
                System.out.println("Enter receiver account id");
                String receiver_id = scanner.nextLine();
                System.out.println("Enter amount");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                if (accounts.containsKey(sender_id) && accounts.containsKey(receiver_id)) {
                accounts.get(sender_id).transfer(amount, accounts.get(receiver_id));
                System.out.println("sender's balance is");
                accounts.get(sender_id).printBalance();
                System.out.println("receiver's balance is");
                accounts.get(receiver_id).printBalance();
                accountsUpdate(accounts, "accounts.txt");
            }else {
                    System.out.println("ID not found.");
                }
        }
        }while (!answer.equals("n"));
         scanner.close();
    }
}