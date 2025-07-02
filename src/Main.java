import java.util.Scanner;
public class Main {
    public static class BankAccount {
        public double balance;
        public BankAccount(double balance) {
            this.balance = balance;
        }
        public void deposit(double amount){
            balance=balance+amount;
        }
        public void withdraw(double amount){
            if(balance>=amount) {
                balance = balance - amount;
            }else {
                System.out.println("impossible operation");
            }
        }
        public void printBalance(){
            System.out.println(balance);
        }
        public void transfer(double amount, BankAccount targetAccount){
            if(balance>=amount) {
                this.withdraw(amount);
                targetAccount.deposit(amount);
            }else {
                System.out.println("impossible operation");
            }
        }

    }


    public static void main(String[] args) {

        BankAccount first = new BankAccount(2000);
        BankAccount second = new BankAccount(1000);

        System.out.println("initial balance");

        first.printBalance();
        second.printBalance();

        first.withdraw(1000);

        System.out.println("after withdraw");

        first.printBalance();
        second.printBalance();

        second.deposit(1000);

        System.out.println("after deposit");

        first.printBalance();
        second.printBalance();

        second.transfer(500,first);

        System.out.println("after transfer");

        first.printBalance();
        second.printBalance();

        System.out.println("insufficient balance");

        second.transfer(5000,first);
        second.withdraw(5000);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to transfer any money? y/n");
        String answer = scanner.nextLine();
        if (answer.equals("y")){
            System.out.println("first account balance:");
            first.printBalance();
            System.out.println("second account balance:");
            second.printBalance();
            System.out.println("enter amount of money to be transferred");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("do you want to transfer it from the first account to second? y/n");
            String answer2 = scanner.nextLine();
            if (answer2.equals("y")){
                first.transfer(amount,second);
            }else{
                second.transfer(amount,first);
            }
            System.out.println("first account balance:");
            first.printBalance();
            System.out.println("second account balance:");
            second.printBalance();
        }
    }
}