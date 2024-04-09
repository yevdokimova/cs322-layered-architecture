package bank.app;
import java.util.Optional;

public class BankServiceImpl implements BankService {

    private final BankAccountRepository bankAccountRepository;

    public BankServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccountDTO createBankAccount(Long userId) {
        BankAccount createdAccount = bankAccountRepository.save(new BankAccount(userId, 0));
        return new BankAccountDTO(createdAccount.getID(), createdAccount.getUserID(), createdAccount.getBalance());
    }

    public double getAccountBalance(Long id) {

        Optional<BankAccount> currentAccount = bankAccountRepository.findById(id);
        if(currentAccount.isPresent()) {
            BankAccount actualAccount = currentAccount.get();
            BankAccountDTO bankAccountDTO = new BankAccountDTO(actualAccount.getID(), actualAccount.getUserID(), actualAccount.getBalance());
            return bankAccountDTO.balance();
        }
        else {
            throw new RuntimeException("Bank account not found");
        }
    }

    public BankAccountDTO getBankAccount(Long id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);

        if (bankAccount.isPresent()) {
            BankAccount actualAccount = bankAccount.get();
            return new BankAccountDTO(actualAccount.getID(), actualAccount.getUserID(), actualAccount.getBalance());
        } else {
            throw new RuntimeException("Bank account not found");
        }
    }

    public double createDebitTransaction(Long id, double amount) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if(amount <= 0) {
            throw new IllegalArgumentException("Invalid amount to createDebitTransaction");
        }
        double balance = getAccountBalance(id);
        balance += amount;
        return getAccountBalance(id);
    }

    public double createCreditTransaction(Long id, double amount) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if(amount <= 0 || amount > getAccountBalance(id)) {
            throw new IllegalArgumentException("Invalid amount to createCreditTransaction");
        }
        double balance = getAccountBalance(id);
        balance -= amount;
        return getAccountBalance(id);
    }
}
