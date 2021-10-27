import java.util.*;
class Account{
    int balance=0;
    public int accountNo;
    int previousTransaction;
    Account(int accountNo){
        this.accountNo=accountNo;
    }

     public void deposit(int amount){
        if (amount != 0) {
            balance = balance + amount;
            previousTransaction = amount;
        }

    }
    public void withdraw(int amount) {
        if (amount != 0 && amount <= balance) {
            balance = balance - amount;
            previousTransaction = -amount;
        } else {
            System.out.println("No sufficient balance available");
        }
    }
    public void getPreviousTransaction() {
        if (previousTransaction > 0) {
            System.out.println("Deposited amount: " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Withdrawn amount: " + Math.abs(previousTransaction));
        } else {
            System.out.println("No transaction happened");
        }
    }
}
public class bankapp {
    Scanner sc = new Scanner(System.in);
    List<Account> accn = new ArrayList<>();
    int i = 6000, s = 5, c = 6;



    void Menu() {
        char option = '\0';
        //Scanner sc = new Scanner(System.in);

        System.out.println("Welcome");
        System.out.println("A.Open a new Account");
        System.out.println("B.Closing a Account");
        System.out.println("C.Deposit Amount");
        System.out.println("D.Withdraw Amount");
        System.out.println("E.View Balance");
        System.out.println("F.previous transaction Report");
        System.out.println("G.Annual Report");
        System.out.println("H.Exit");

        do {
            int account;
            System.out.println("Select the Option...");
            option = sc.next().charAt(0);

            switch (option) {
                case 'A':
                    opening_account();
                    break;
                case 'B':
                    System.out.println("Enter your account number");
                    account = sc.nextInt();
                    closing_account(account);
                    break;
                case 'C':
                    System.out.println("Enter your account number: ");
                    account = sc.nextInt();
                    Account u = getAccountFromAccountNo(account);
                    if (u==null) {
                        System.out.println("Please enter the correct account number");
                        break;
                    } else {
                        System.out.println("Enter the amount to deposit");
                        int amount = sc.nextInt();
                        u.deposit(amount);
                    }
                    break;
                case 'D':
                    System.out.println("Enter your account number: ");
                    account = sc.nextInt();
                    u = getAccountFromAccountNo(account);
                    if (u==null) {
                        System.out.println("Please enter the correct account number");
                        break;
                    } else {
                        System.out.println("Enter the amount to withdraw");
                        int amount1 = sc.nextInt();
                        u.withdraw(amount1);
                    }
                    break;
                case 'E':
                    System.out.println("Enter your account number: ");
                    account = sc.nextInt();
                    u = getAccountFromAccountNo(account);
                    if (u==null) {
                        System.out.println("Please enter the correct account number");
                        break;
                    } else {
                        System.out.println("Balance amount: " + u.balance);
                    }
                    break;
                case 'F':
                    System.out.println("Enter your account number: ");
                    account = sc.nextInt();
                    u = getAccountFromAccountNo(account);
                    if (u==null) {
                        System.out.println("Please enter the correct account number");
                        break;
                    } else {
                        u.getPreviousTransaction();
                    }
                    break;
                case 'G':
                    System.out.println("Number of Savings accounts: " + s + "\nNumber of current accounts: " + c);
                    break;
                case 'H':
                    System.out.println("...............");
                    break;
                default:
                    System.out.println("No other option available");

            }

        } while (option != 'H');
    }

    public static void main(String[] args) {
        bankapp obj1 = new bankapp();
        obj1.Menu();

    }

    public void opening_account() {
        System.out.println("Enter your name");
        String name = sc.next();
        System.out.println("Enter your Contact");
        int phone = sc.nextInt();
        System.out.println("Enter the account type savings/current");
        String acc = sc.next();
        int a = i++;
        System.out.println("Your Account number is: " + (a));
        Account account = new Account(a);
        accn.add(account);
        if (acc.equals("savings")) {
            System.out.println("Deposit minimum balance amount of Rs.1000");
            s++;
        } else {
            System.out.println("Your account is ready to use");
            c++;
        }

    }

    public void closing_account(int accountNo) {
        Account account = null;
        int j;
        for ( j = 0; j < accn.size(); j++) {
            if (accountNo == accn.get(j).accountNo) {
                //accn.remove(j);
                account = accn.get(j);
                break;
            }
        }
        if (account == null)
            System.out.println("Please enter the correct account number");
        else {
            System.out.println("Your account is closed successfully");
            accn.remove(account);

        }
    }

    public Account getAccountFromAccountNo(int accountNo) {
        Account account = null;
        for (int j = 0; j < accn.size(); j++) {
            if (accountNo == accn.get(j).accountNo) {
                account = accn.get(j);
                break;
            }
        }
        return account;

    }
}



