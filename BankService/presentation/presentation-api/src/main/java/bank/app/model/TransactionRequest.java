package bank.app.model;
import jakarta.annotation.Nonnull;

public record TransactionRequest (@Nonnull double amount) {
}