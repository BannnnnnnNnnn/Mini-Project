package com.example.miniproject.service.impl;

import com.example.miniproject.DTO.AccountDTO;
import com.example.miniproject.converter.AccountConverter;
import com.example.miniproject.model.Account;
import com.example.miniproject.repository.AccountRepository;
import com.example.miniproject.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountConverter accountConverter;

    @Override
    public AccountDTO Logging(String userName, String password) {
        return accountConverter.toDTO(accountRepository.findByUsernameAndPassword(userName, password));
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.findAll().stream()
                .map(accountConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean createAccount(AccountDTO accountDTO) {
        Account account = accountRepository.findAccountByUsername(accountDTO.getUsername());
        if (account == null) {
            accountRepository.save(accountConverter.toEntity(accountDTO));
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAccount(String userName, AccountDTO updatedAccountDTO) {
        Account account = accountRepository.findAccountByUsername(userName);
        if (account != null) {
            Account existingAccount = account;

            // Cập nhật thông tin tài khoản từ DTO
            BeanUtils.copyProperties(updatedAccountDTO, existingAccount, "username");

            // Lưu tài khoản đã cập nhật vào cơ sở dữ liệu
            accountRepository.save(existingAccount);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAccount(String username) {
        Account account = accountRepository.findAccountByUsername(username);

        if (account != null) {
            accountRepository.delete(account);
            return true;
        } else {
            return false;
        }
    }
}
