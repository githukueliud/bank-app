package com.example.bank_app.service;

import com.example.bank_app.dto.AccountDto;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(long id);

    AccountDto deposit(long id, double amount);
}
