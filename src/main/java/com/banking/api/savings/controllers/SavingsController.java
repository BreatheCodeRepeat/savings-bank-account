package com.banking.api.savings.controllers;

import com.banking.api.savings.dto.DepositWithdrawDTO;
import com.banking.api.savings.models.User;
import com.banking.api.savings.models.enums.MessageEnums;
import com.banking.api.savings.models.enums.UserMessage;
import com.banking.api.savings.services.SavingsService;
import com.banking.api.savings.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/savingsApi")
public class SavingsController {

    private final SavingsService savingsService;
    private final UserService userService;

    @Autowired
    public SavingsController(SavingsService savingsService, UserService userService) {
        this.savingsService = savingsService;
        this.userService = userService;
    }

    @GetMapping
    public double getAmountForUser(@RequestParam String userName, @RequestParam String password) {
        Optional<User> user = userService.getUserNameAndPassWord(userName, password);
        return user.map(savingsService::getSavingAccountBalanceByUser).orElse(0.0);
    }

    @PostMapping
    public MessageEnums widthdrawMoneyFromUserSavingAccount(@RequestParam String userName,
                                                            @RequestParam String password,
                                                            @RequestBody DepositWithdrawDTO dto) {

        Optional<User> user = userService.getUserNameAndPassWord(userName, password);
        if (user.isPresent()) {

            if (dto.getType().equals("withdraw")) {
                return savingsService.withdrawFromSavingsAccount(user.get(), dto.getAmount());
            } else if (dto.getType().equals("deposit")) {
                return savingsService.depositToSavingAccount(user.get(), dto.getAmount());
            }
        }
        return UserMessage.USER_DOES_NOT_EXIST;
    }

    @PutMapping
    public MessageEnums createNewSavingsAccount(@RequestParam String userName,
                                                @RequestParam String password) {

        Optional<User> user = userService.getUserNameAndPassWord(userName, password);
        return user.map(savingsService::openSavingsAccount).orElse(UserMessage.USER_DOES_NOT_EXIST);
    }
}
