package Bank;
import java.util.*;

abstract class Bank {
    public final String BankName = "VinodBank";

    public final void bankAddress() {
        System.out.println("Himayath Nagar, Hyderabad, Telangana 500075");
    }
}

class Account extends Bank {
    private double balance;
    private long accno;
    private int pin;
    private double loanAmount;
    private String userName;
    private int age;

    Account(long accno, String name, int age, int pin) {
        this.accno = accno;
        this.userName = name;
        this.age = age;
        this.pin = pin;
    }

    public long getAccno() {
        return accno;
    }

    public String getUserName() {
        return userName;
    }

    public void changePin(int oldpin, int newpin) {
        if (this.pin == oldpin) {
            this.pin = newpin;
            System.out.println("PIN changed successfully!");
        } else {
            System.out.println("Wrong old PIN!");
        }
    }

    public void deposit(int pin, double amount) {
        if (this.pin != pin) {
            System.out.println("Wrong PIN!");
            return;
        }

        if (amount <= 0) {
            System.out.println("Enter a valid amount!");
            return;
        }

        double deduction = 0;

        if (loanAmount > 0) {
            deduction = Math.min(loanAmount, amount * 0.03);
            loanAmount -= deduction;
        }

        balance += amount - deduction;
        System.out.println((amount - deduction) +
                           " credited successfully!");
    }

    public void deposit(long accno, String name, double amount) {
        if (this.accno == accno && this.userName.equals(name)) {
            if (amount <= 0) {
                System.out.println("Enter a valid amount!");
                return;
            }

            double deduction = 0;

            if (loanAmount > 0) {
                deduction = Math.min(loanAmount, amount * 0.03);
                loanAmount -= deduction;
            }

            balance += amount - deduction;
            System.out.println((amount - deduction) +
                               " credited successfully!");
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    public void getloan(double amount, int pin) {
        if (this.pin != pin) {
            System.out.println("Wrong PIN!");
            return;
        }

        if (amount <= 0) {
            System.out.println("Enter a valid loan amount!");
            return;
        }

        balance += amount;
        loanAmount += amount;

        System.out.println("Loan of " + amount + " credited!");
        System.out.println("3% of future deposits will go towards loan repayment.");
    }

    public void accdetails(int pin) {
        if (this.pin == pin) {
            System.out.println("Account Number: " + accno);
            System.out.println("Name: " + userName);
            System.out.println("Age: " + age);
        } else {
            System.out.println("Wrong PIN!");
        }
    }

    public void withdraw(int pin, double amount) {
        if (this.pin != pin) {
            System.out.println("Wrong PIN!");
            return;
        }

        if (amount <= 0 || balance < amount) {
            System.out.println("Invalid amount or insufficient balance!");
            return;
        }

        balance -= amount;
        System.out.println(amount + " debited successfully!");
    }

    public void getbalance(int pin) {
        if (this.pin == pin) {
            System.out.println("Current balance: Rs. " + balance);
        } else {
            System.out.println("Wrong PIN!");
        }
    }

    public void getLoanamount(int pin) {
        if (this.pin == pin) {
            System.out.println("Outstanding loan: Rs. " + loanAmount);
        } else {
            System.out.println("Wrong PIN!");
        }
    }
    //Money Transfer!
public void transfer(Account receiver, int pin, double amount) {

    if (this.pin != pin) {
        System.out.println("Wrong PIN!");
        return;
    }

    if (receiver == this) {
        System.out.println("Cannot transfer to the same account!");
        return;
    }

    if (amount <= 0 || this.balance < amount) {
        System.out.println("Invalid amount or insufficient balance!");
        return;
    }

    this.balance -= amount;
    receiver.balance += amount;

    System.out.println("₹" + amount + " transferred successfully!");
}

}



public class test {

    // Find the correct account in the ArrayList
    public static Account findAccount(
            ArrayList<Account> accounts, long accno) {

        for (Account a : accounts) {
            if (a.getAccno() == accno) {
                return a;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n===== VINODBANK =====");
            System.out.println("0. Create Account");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Get Loan");
            System.out.println("5. Check Loan Amount");
            System.out.println("6. Account Details");
            System.out.println("7. Change PIN");
            System.out.println("8. Transfer money");
            System.out.println("9.Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 0: {
                    System.out.print("Enter account number: ");
                    long accno = sc.nextLong();

                    if (findAccount(accounts, accno) != null) {
                        System.out.println("Account number already exists!");
                        break;
                    }

                    sc.nextLine();

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();

                    if (age <= 0) {
                        System.out.println("Invalid age!");
                        break;
                    }

                    System.out.print("Set PIN: ");
                    int pin = sc.nextInt();

                    Account newAccount =
                            new Account(accno, name, age, pin);

                    accounts.add(newAccount);

                    System.out.println("Account created successfully!");
                    System.out.println("Welcome, " + name + "!");
                    break;
                }

                case 1: {
                    System.out.print("Enter account number: ");
                    long accno = sc.nextLong();

                    Account found = findAccount(accounts, accno);

                    if (found == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int pin = sc.nextInt();

                    System.out.print("Enter deposit amount: ");
                    double amount = sc.nextDouble();

                    found.deposit(pin, amount);
                    break;
                }

                case 2: {
                    System.out.print("Enter account number: ");
                    long accno = sc.nextLong();

                    Account found = findAccount(accounts, accno);

                    if (found == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int pin = sc.nextInt();

                    System.out.print("Enter withdrawal amount: ");
                    double amount = sc.nextDouble();

                    found.withdraw(pin, amount);
                    break;
                }

                case 3: {
                    System.out.print("Enter account number: ");
                    long accno = sc.nextLong();

                    Account found = findAccount(accounts, accno);

                    if (found == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int pin = sc.nextInt();

                    found.getbalance(pin);
                    break;
                }

                case 4: {
                    System.out.print("Enter account number: ");
                    long accno = sc.nextLong();

                    Account found = findAccount(accounts, accno);

                    if (found == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int pin = sc.nextInt();

                    System.out.print("Enter loan amount: ");
                    double amount = sc.nextDouble();

                    found.getloan(amount, pin);
                    break;
                }

                case 5: {
                    System.out.print("Enter account number: ");
                    long accno = sc.nextLong();

                    Account found = findAccount(accounts, accno);

                    if (found == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int pin = sc.nextInt();

                    found.getLoanamount(pin);
                    break;
                }

                case 6: {
                    System.out.print("Enter account number: ");
                    long accno = sc.nextLong();

                    Account found = findAccount(accounts, accno);

                    if (found == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int pin = sc.nextInt();

                    found.accdetails(pin);
                    break;
                }

                case 7: {
                    System.out.print("Enter account number: ");
                    long accno = sc.nextLong();

                    Account found = findAccount(accounts, accno);

                    if (found == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter old PIN: ");
                    int oldPin = sc.nextInt();

                    System.out.print("Enter new PIN: ");
                    int newPin = sc.nextInt();

                    found.changePin(oldPin, newPin);
                    break;
                }
               case 8: {
                            System.out.print("Enter your account number: ");
                            long senderNo = sc.nextLong();

                            Account sender = findAccount(accounts, senderNo);

                            if (sender == null) {
                                System.out.println("Sender account not found!");
                                break;
                            }

                            System.out.print("Enter receiver account number: ");
                            long receiverNo = sc.nextLong();

                            Account receiver = findAccount(accounts, receiverNo);

                            if (receiver == null) {
                                System.out.println("Receiver account not found!");
                                break;
                            }

                            System.out.print("Enter your PIN: ");
                            int pin = sc.nextInt();

                            System.out.print("Enter transfer amount: ");
                            double amount = sc.nextDouble();

                            sender.transfer(receiver, pin, amount);
                            break;
                        }

                case 9:
                    System.out.println("Thank you for using VinodBank!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 9);

        sc.close();
    }
}