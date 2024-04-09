package bank.app;
import bank.app.model.*;

public interface BankAccountController {
    public bank.app.BankAccountDTO createBankAccount(CreateBankAccountRequest request);

    public double debit(Long id, TransactionRequest request);

    public double credit(Long id, TransactionRequest request);

}
