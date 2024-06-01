package com.example.bank_app.mapper;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.entity.Account;

public class AccountMapper {

    public  static Account mapTOAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );

        return account;
    }

    public  static AccountDto mapTOAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );

        return accountDto;
    }
}
