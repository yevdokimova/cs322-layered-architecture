package bank.app;
import jakarta.persistence.*;


@Entity
@Table(name = "BankAccount")

public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private double balance;

    public BankAccount() { }

    public BankAccount(Long userID, double balance) {
        this.balance = balance;
        this.userId = userID;
    }

    public Long getAccountId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
