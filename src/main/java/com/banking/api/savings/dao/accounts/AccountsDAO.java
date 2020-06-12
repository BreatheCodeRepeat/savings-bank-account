package com.banking.api.savings.dao.accounts;

import com.banking.api.savings.models.User;

import java.util.Optional;

public interface AccountsDAO {
    Optional<User> getUser(String password, String userName);
}
