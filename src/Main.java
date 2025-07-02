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
    }
}