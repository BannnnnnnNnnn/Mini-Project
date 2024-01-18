package com.example.miniproject.converter;

import com.example.miniproject.DTO.AccountDTO;
import com.example.miniproject.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public Account toEntity(AccountDTO accountDTO){
        if(accountDTO!=null){

            Account account = new Account();
            account.setUsername(accountDTO.getUsername());
            account.setPassword(accountDTO.getPassword());
            account.setRole(accountDTO.getRole());
            account.setStatus(accountDTO.isStatus());

            return account;
        }
        return null;
    }

    public AccountDTO toDTO(Account account){
        if(account!=null){

            AccountDTO DTO = new AccountDTO();
            DTO.setUsername(account.getUsername());
            DTO.setPassword(account.getPassword());
            DTO.setRole(account.getRole());
            DTO.setStatus(account.isStatus());

            return DTO;
        }
        return  null;
    }

}
