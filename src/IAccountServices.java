import java.sql.SQLException;

// Interface for operation actions

public interface IAccountServices {

    void OpenSavingsAccount(long accountId, long amountToDeposit) throws SQLException;
    void OpenCurrentAccount(long accountId);
    void Withdraw(long accountId, long AmountToWithdraw);
    void Deposit(long accountId, long AmountToDeposit);
}
