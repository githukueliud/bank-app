package com.example.bank_app.service.impl;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repository.AccountRepository;
import com.example.bank_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {
    //dependency injection
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapTOAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapTOAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist!"));
        return AccountMapper.mapTOAccountDto(account);
    }

    @Override
    public AccountDto deposit(long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist!"));

        double newAmount = account.getBalance() + amount;
        account.setBalance(newAmount);
        accountRepository.save(account);
        return AccountMapper.mapTOAccountDto(account);
    }

    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist!"));
        if(account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance!");
        }
        double newAmount = account.getBalance() - amount;
        account.setBalance(newAmount);
        accountRepository.save(account);
        return AccountMapper.mapTOAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map((account -> AccountMapper.mapTOAccountDto(account))).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist!"));
        accountRepository.delete(account);
    }
}
