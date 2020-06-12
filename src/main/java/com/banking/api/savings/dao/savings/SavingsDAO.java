package com.banking.api.savings.dao.savings;

import com.banking.api.savings.models.User;
import com.banking.api.savings.models.enums.SavingsAccountStateEnum;

public interface SavingsDAO {
    boolean hasSavingAccount(User user);

    double getBalance(User user);

    boolean updateAccountValue(User user, double amount);

    boolean createAccount(User user, SavingsAccountStateEnum status);
}
