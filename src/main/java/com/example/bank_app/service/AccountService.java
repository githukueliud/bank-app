package com.example.bank_app.service;

import com.example.bank_app.dto.AccountDto;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(long id);

    AccountDto deposit(long id, double amount);

    AccountDto withdraw(long id, double amount);

    List<AccountDto> getAllAccounts();
}
