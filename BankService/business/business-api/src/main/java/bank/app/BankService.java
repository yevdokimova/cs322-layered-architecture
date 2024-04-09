package bank.app;

public interface BankService {
    public BankAccountDTO createBankAccount(Long userId);
    public BankAccountDTO getBankAccount(Long bankAccountID);
    public double getAccountBalance(Long id);
    public double createDebitTransaction(Long id, double amount);
    public double createCreditTransaction(Long id, double amount);
}