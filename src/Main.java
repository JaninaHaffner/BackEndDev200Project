import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

/* Main class has a menu function / method that can be placed in a separate class upon upgrading the program.
*  Menu uses a switch case scenario to navigate options for the user.
*  There is a quit method in this class as well, that is called from the menu.
*  There is a lot of functionality and classes that can be added at a later stage. */

public class Main {

    public static void main(String [] arg) throws SQLException {

        Menu();

    }
    public static void Menu() throws SQLException {

        Scanner input = new Scanner(System.in);
        int selection;
        long accountId;
        long amount;
        ResultSet accountInfo;
        String accountType = "";
        IAccountServices savings = new SavingsAccount();
        IAccountServices current = new CurrentAccount();

        System.out.println("""
                Please select one of the following options:
                1. Open a Savings Account
                2. Open a Current Account
                3. Deposit Money
                4. Withdraw Money
                5. Quit
                Enter 1 or 2 or 3 or 4 or 5 to select the option.
                """);
        selection = input.nextInt();

        switch (selection){
            case 1:
                System.out.println("Please note that all savings accounts must have a minimum of R1000.00 to be deposited in order to open the account");
                System.out.println("Enter an account ID: ");
                accountId = input.nextInt();
                System.out.println("Enter the amount you would like to deposit: ");
                amount = input.nextLong();
                savings.OpenSavingsAccount(accountId, amount);
                Menu();

            case 2:
                System.out.println("Enter an account ID: ");
                accountId = input.nextInt();
                current.OpenCurrentAccount(accountId);
                Menu();

            case 3:
                System.out.println("Enter the account ID to Deposit the money into: ");
                accountId = input.nextInt();
                System.out.println("Enter the amount you would like to deposit: ");
                amount = input.nextLong();
                accountInfo = SystemDB.CheckAccountInformation(accountId);
                try {

                    while (accountInfo.next()) {
                        accountType = accountInfo.getString("accountType");
                    }
                    if(Objects.equals(accountType, "Savings Account")){
                        savings.Deposit(accountId, amount);
                    } else {
                        current.Deposit(accountId, amount);
                    }
                }catch (SQLException e) {
                    System.out.println("Account could not be found.");
                }

                Menu();

            case 4:
                System.out.println("Enter the account ID you would like to withdraw from: ");
                accountId = input.nextInt();
                System.out.println("Enter the amount you would like to withdraw: ");
                amount = input.nextLong();
                accountInfo = SystemDB.CheckAccountInformation(accountId);
                try {

                    while (accountInfo.next()) {
                        accountType = accountInfo.getString("accountType");
                    }
                    if(Objects.equals(accountType, "Savings Account")){
                        savings.Withdraw(accountId, amount);
                    } else {
                        current.Withdraw(accountId, amount);
                    }
                }catch (SQLException e) {
                    System.out.println("Account could not be found.");
                }
                Menu();

            case 5:
                Quit();
                break;

            default:
                System.out.println("You did not make a valid selection.");
                Menu();
        }
    }
    public static void Quit(){
        System.out.println("Good Bey!");
    }
}
