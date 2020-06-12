package com.banking.api.savings.dao.savings;

import com.banking.api.savings.dao.savings.SavingsDAO;
import com.banking.api.savings.models.SavingAccount;
import com.banking.api.savings.models.User;
import com.banking.api.savings.models.enums.SavingsAccountStateEnum;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("inMemorySavingsDB")
public class SavingsRepositoryInMemory implements SavingsDAO {

    private static Map<UUID, SavingAccount> savingsAccounts = new HashMap<>();

    @Override
    public boolean hasSavingAccount(User user) {
        if (savingsAccounts.get(user.getUserID()) == null) {
            return false;
        }
        return true;
    }

    @Override
    public double getBalance(User user) {
        return savingsAccounts.getOrDefault(user.getUserID(), new SavingAccount(0))
                .getAmount();
    }

    @Override
    public boolean updateAccountValue(User userID, double amount) {
        SavingAccount savingAccount = savingsAccounts.get(userID.getUserID());

        if (savingAccount == null) {
            return false;
        }
        savingAccount.setAmount(amount);
        return true;
    }

    @Override
    public boolean createAccount(User user, SavingsAccountStateEnum status) {
        UUID userID = user.getUserID();
        SavingAccount savingAccount = savingsAccounts.get(userID);

        if (savingAccount != null) {
            return false;
        }

        savingsAccounts.put(userID, new SavingAccount(0, status));
        return true;
    }
}
