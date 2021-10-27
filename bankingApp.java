import java.util.*;
class Account{
    public boolean isActive=true;
    int balance=0;
    public int accountNo;
    public int phone;
    public String name;
    public String accType;
    int previousTransaction;
    public AccountType typeOfAcc;
    List<Integer> transaction = new ArrayList<>(10);
    //PriorityQueue<Integer> transaction = new PriorityQueue<>(10);
    Account(int accountNo,String name,int phone,String accType){
        this.accountNo=accountNo;
        this.name=name;
        this.phone=phone;
        this.accType=accType;
    }
    public void deposit(int amount){
        if (amount > 0) {
            balance = balance + amount;
            previousTransaction = amount;
            transaction.add(previousTransaction);
            System.out.println("You have deposited the amount of Rs."+amount+" successfully\nBalance amount is : Rs."+balance);
            isActive=true;
        }
        else
        {
            System.out.println("Enter the valid amount");
            isActive=false;
        }

    }
    public void withdraw(int amount) {
        if (amount != 0 && amount <= balance) {
            balance = balance - amount;
            previousTransaction = -amount;
            transaction.add(previousTransaction);
            System.out.println("You have withdrawn the amount of Rs."+amount+" successfully.\nBalance amount is : Rs."+balance);
            isActive=true;
        } else {
            System.out.println("No sufficient balance available");
            isActive=false;
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

            System.out.println(transaction);
        }

}
enum AccountType {
    SAVINGS,
    CURRENT
}
public class bankingApp {
    Scanner sc = new Scanner(System.in);
    List<Account> accNoList = new ArrayList<>();
    int s = 0, c = 0;

    void Menu() {
        char option;


        do {
            System.out.println("\n*************************************************\n");
            System.out.println("A.Open a new Account");
            System.out.println("B.Closing a Account");
            System.out.println("C.Deposit Amount");
            System.out.println("D.Withdraw Amount");
            System.out.println("E.View Balance");
            System.out.println("F.previous transaction");
            System.out.println("G.Account Details");
            System.out.println("H.Annual Report");
            System.out.println("I.Exit");
            System.out.println("Select the Option.......");

            //Menu();
            option = sc.next().charAt(0);
            switch (option) {
                case 'A':
                    opening_account();
                    break;
                case 'B':
                    getAccountNo();
                    closing_account(accountNumber);
                    break;
                case 'C':
                    getAccountNo();
                    Account foundAccount = getAccountFromAccountNo(accountNumber);
                    if (foundAccount==null) {
                        System.out.println("Please enter the correct account number");
                        break;
                    } else {
                        System.out.println("Enter the amount to deposit");
                        int amount = sc.nextInt();
                        foundAccount.deposit(amount);
                    }
                    break;
                case 'D':
                    getAccountNo();
                    foundAccount = getAccountFromAccountNo(accountNumber);
                    if (foundAccount==null) {
                        System.out.println("Please enter the correct account number");
                        break;
                    } else {
                        System.out.println("Enter the amount to withdraw");
                        int amount1 = sc.nextInt();
                        foundAccount.withdraw(amount1);
                    }
                    break;
                case 'E':
                    getAccountNo();
                    foundAccount = getAccountFromAccountNo(accountNumber);
                    if (foundAccount==null) {
                        System.out.println("Please enter the correct account number");
                        break;
                    } else {
                        System.out.println("Balance amount: " + foundAccount.balance);
                    }
                    break;
                case 'F':
                    getAccountNo();
                    foundAccount = getAccountFromAccountNo(accountNumber);
                    if (foundAccount==null) {
                        System.out.println("Please enter the correct account number");
                        break;
                    } else {
                        foundAccount.getPreviousTransaction();
                    }
                    break;
                case 'G':
                    getAccountNo();
                    foundAccount = getAccountFromAccountNo(accountNumber);
                    System.out.println("Name: " + foundAccount.name);
                    System.out.println("Phone no: " + foundAccount.phone);
                    System.out.println("Account Type: " + foundAccount.accType);
                    System.out.println("Balance amount: " + foundAccount.balance);
                    break;
                case 'H':
                    System.out.println("Number of Savings accounts: " + s + "\nNumber of current accounts: " + c);
                    break;
                case 'I':
                    System.out.println("...............");
                    break;
                default:
                    System.out.println("Please enter the valid option");
            }

        } while (option != 'I');
    }

    public static void main(String[] args) {
        bankingApp obj1 = new bankingApp();
        obj1.Menu();

    }
    int accountNumber;
    void getAccountNo( ) {
        System.out.println("Enter your account number: ");
        accountNumber = sc.nextInt();
    }

    public void opening_account() {
        System.out.println("Enter your name");
        String name = sc.next();
        System.out.println("Enter your Contact");
        int phone = sc.nextInt();
        System.out.println("Enter the account type Savings/Current");
        String accType=sc.next();
        //Account.AccountType acc = Account.AccountType.valueOf(accountType);
        int a = 10000 + new Random().nextInt(90000);
        System.out.println("Your Account number is: " + (a));
        Account account = new Account(a,name, phone,accType);
        accNoList.add(account);

       AccountType type= AccountType.valueOf(accType);
        account.typeOfAcc=type;
        switch(type)
        {
            case SAVINGS:
                System.out.println("Deposit minimum balance amount of Rs.1000");
                s++;
                break;
            case CURRENT:
                System.out.println("Your account is ready to use");
                c++;
                break;
        }

    }

    public void closing_account(int accountNumber) {
        Account account = null;
        int j;
        for (j = 0; j < accNoList.size(); j++) {
            if (accountNumber == accNoList.get(j).accountNo) {
                account = accNoList.get(j);
                break;
            }
        }
        if (account == null)
            System.out.println("Please enter the correct account number");
        else {
            switch(account.typeOfAcc)
            {
                case SAVINGS:
                    System.out.println("Deposit minimum balance amount of Rs.1000");
                    s--;
                    break;
                case CURRENT:
                    System.out.println("Your account is ready to use");
                    c--;
                    break;
            }
            account.isActive=false;

        }
    }

    public Account getAccountFromAccountNo(int accountNumber) {
        Account account = null;
        for (Account value : accNoList) {
            if (accountNumber == value.accountNo) {
                account = value;
                break;
            }
        }
        return account;
    }
}
