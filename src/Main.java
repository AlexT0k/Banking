import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Main {
    public static class BankAccount {
        public double balance;
        public String name;
        short id;

        public BankAccount(String name, double balance, short id) {
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
            } else {
                System.out.println("impossible operation");
            }
        }

    }

    public static List<BankAccount> readAccounts(String fileName) {
        List<BankAccount> accounts = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String name = parts[0].trim();
                    double balance = Double.parseDouble(parts[1].trim());
                    short id = Short.parseShort(parts[2].trim());
                    accounts.add(new BankAccount(name, balance, id));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }
        return accounts;
    }


    public static void main(String[] args) {
        List<BankAccount> accounts = readAccounts("accounts.txt");
        System.out.println("Accounts loaded:");
        for(int i=0;i<accounts.size();i++){
            System.out.println(accounts.get(i).name+" "+accounts.get(i).balance+" "+accounts.get(i).id);
        }
    }
}