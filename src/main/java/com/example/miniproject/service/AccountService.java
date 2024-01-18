package com.example.miniproject.service;

import com.example.miniproject.DTO.AccountDTO;
import java.util.List;

public interface AccountService {
    AccountDTO Logging(String userName, String password);
    List<AccountDTO> getAll();
    boolean createAccount(AccountDTO accountDTO);
    boolean updateAccount(String username, AccountDTO updatedAccountDTO);
    boolean deleteAccount(String username);
}
