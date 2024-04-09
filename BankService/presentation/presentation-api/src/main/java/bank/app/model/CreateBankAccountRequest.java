package bank.app.model;

import jakarta.annotation.Nonnull;

public record CreateBankAccountRequest(@Nonnull Long userID) {
}
