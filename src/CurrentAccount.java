import java.sql.ResultSet;
import java.sql.SQLException;

/*  Current account class
 *  There are three methods in the class
 *  Opening a savings account, Deposit and Withdraw that is called from the interface class
 *  This class implements the interface class
 *  Using if statements and try catch blocks to navigate the different methods and
 *  manage the exceptions.  */

public class CurrentAccount implements IAccountServices {

    long accountNumber;
    String accountType = "Current Account";
    long overdraft = 0;
    long amountToDeposit = 0;
    long balance;
    int accountCreated;
    long adjustedBalance;
    ResultSet accountInfo;
    Accounts newAccount = new Accounts();

    public void OpenCurrentAccount(long accountId) {

        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountId(accountId);
        newAccount.setAccountType(accountType);
        newAccount.setBalance(amountToDeposit);
        newAccount.setOverdraft(overdraft);

        accountCreated = SystemDB.SavedNewAccount(newAccount.getAccountId(), newAccount.getAccountType(), newAccount.getBalance(), newAccount.getOverdraft());

        if (accountCreated != 0) {
            System.out.println("Your account has successfully been created.");
        }
    }

    @Override
    public void Withdraw(long accountId, long AmountToWithdraw) {

        try {
            accountInfo = SystemDB.CheckAccountInformation(accountId);

            while (accountInfo.next()) {

                accountNumber = accountInfo.getInt("accountNumber");
                accountType = accountInfo.getString("accountType");
                balance = accountInfo.getInt("balance");
                overdraft = accountInfo.getInt("overdraft");
            }

            adjustedBalance = (balance + overdraft) - AmountToWithdraw;

            if (adjustedBalance >= 0){
                balance = adjustedBalance - overdraft;
                long overdraftBalance = adjustedBalance - overdraft;
                accountCreated = SystemDB.UpdateAccountBalance(accountId, balance);
                if (accountCreated != 0){
                    System.out.println("Your account was successfully credited. Your new balance is: R" + overdraftBalance);
                } else {
                    System.out.println("Your account could not be credited.");
                }
            } else {
                System.out.println("You do not have enough funds in your account to withdraw.");
            }

        } catch (SQLException e) {
            System.out.println("Your account could not be found.");
        }
    }

    public void Deposit(long accountId, long AmountToDeposit) {

        try {
            accountInfo = SystemDB.CheckAccountInformation(accountId);

            while (accountInfo.next()) {

                accountNumber = accountInfo.getInt("accountNumber");
                accountType = accountInfo.getString("accountType");
                balance = accountInfo.getInt("balance");
                overdraft = accountInfo.getInt("overdraft");
            }

            adjustedBalance = balance + AmountToDeposit;

            accountCreated = SystemDB.UpdateAccountBalance(accountId, adjustedBalance);
            if (accountCreated != 0){
                System.out.println("Your account was successfully debited. Your new balance is: R" + adjustedBalance);
            } else {
                System.out.println("Your account could not be debited.");
            }

        } catch (SQLException e) {
            System.out.println("Your account could not be found.");
        }
    }

    @Override
    public void OpenSavingsAccount(long accountId, long amountToDeposit) {

    }
}
