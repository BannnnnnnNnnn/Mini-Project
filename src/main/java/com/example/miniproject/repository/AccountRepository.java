package com.example.miniproject.repository;

import com.example.miniproject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsernameAndPassword(String username, String password);
    Account findAccountByUsername(String username);

}
