package bank.app;
import bank.app.model.*;

public interface BankAccountController {
    public BankAccountDTO createBankAccount(CreateBankAccountRequest request);

    public double createDebitTransaction(Long id, TransactionRequest request);

    public double createCreditTransaction(Long id, TransactionRequest request);

}
