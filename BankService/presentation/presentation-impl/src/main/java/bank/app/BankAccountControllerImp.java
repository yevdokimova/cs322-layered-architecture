package bank.app;

import bank.app.model.CreateBankAccountRequest;
import bank.app.model.TransactionRequest;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/v1/account")
public class BankAccountControllerImp implements BankAccountController {

    private final BankService BankService;

    public BankAccountControllerImp(BankService BankService) {
        this.BankService = BankService;
    }

    @Override
    @PostMapping()
    public BankAccountDTO createBankAccount(@RequestBody CreateBankAccountRequest request) {
        return BankService.createBankAccount(request.userID());
    }

    @Override
    @PostMapping("/debit/{id}")
    public double debit(@PathVariable Long id, @RequestBody TransactionRequest request) {
        BankAccountDTO bankAccount = BankService.getBankAccount(id);
        return BankService.createDebitTransaction(bankAccount.id(), request.amount());
    }

    @Override
    @PostMapping("/credit/{id}")
    public double credit(@PathVariable Long id, @RequestBody TransactionRequest request) {
        BankAccountDTO bankAccount = BankService.getBankAccount(id);
        return BankService.createCreditTransaction(bankAccount.id(), request.amount());
    }
}
