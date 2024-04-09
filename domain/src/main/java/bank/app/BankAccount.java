package bank.app;
import jakarta.persistence.*;


@Entity
@Table(name = "bankaccount")

public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private double balance;

    public BankAccount() { }

    public BankAccount(Long userID, double balance) {
        this.balance = balance;
        this.user_id = userID;
    }//added by me

    public Long getID() {
        return id;
    }

    public Long getUserID() {
        return user_id;
    }

    public double getBalance() {
        return balance;
    }

    public double debit(double amount, double amounted) {
        balance += amount;
        return balance;
    }

    public double credit (double amount, double amounted) throws IllegalStateException {
        if(amount > balance) {
            throw new IllegalStateException("The balance is below the requested amount");
        }
        balance -= amount;
        return balance;
    }

}
