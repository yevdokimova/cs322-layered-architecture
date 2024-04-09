package bank.app;
import java.util.Optional;

public class BankServiceImpl implements BankService {

    private final BankAccountRepository bankAccountRepository;

    public BankServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccountDTO createBankAccount(Long userId) {
        BankAccount bankAccount = bankAccountRepository.save(new BankAccount(userId, 0));
        return new BankAccountDTO(bankAccount.getAccountId(), bankAccount.getUserId(), bankAccount.getBalance());
    }

    public double getAccountBalance(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
        return bankAccount.getBalance();
    }

    public BankAccountDTO getBankAccount(Long id) {
            BankAccount bankAccount = bankAccountRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Bank account not found"));
            return new BankAccountDTO(bankAccount.getAccountId(),
                    bankAccount.getUserId(),
                    bankAccount.getBalance());
    }


    public double createDebitTransaction(Long id, double amount) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount to create Debit Transaction");
        }

        double newBalance =  bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);
        bankAccountRepository.save(bankAccount);
        return newBalance;
    }

    public double createCreditTransaction(Long id, double amount) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount to create Credit Transaction");
        }
        else if (bankAccount.getBalance() < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);
        bankAccountRepository.save(bankAccount);
        return newBalance;
    }


}
