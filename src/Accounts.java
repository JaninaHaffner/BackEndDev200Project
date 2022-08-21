/* Object class to create an account.  */

public class Accounts {

    long accountNumber;
    long accountId;
    String accountType;
    long balance;
    long overdraft;

    public void Accounts(long aAccountNumber, long aAccountId, String aAccountType, long aBalance, long aOverdraft)
    {
        accountNumber = aAccountNumber;
        accountId = aAccountId;
        accountType = aAccountType;
        balance = aBalance;
        overdraft = aOverdraft;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(long overdraft) {
        this.overdraft = overdraft;
    }
}
