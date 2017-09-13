package com.tinhcao.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinhcao.model.Account;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Repository(value = "accountRepository")
public class AccountRepositoryImpl implements AccountRepository {
    /**
     * Get Account detail by account id
     *
     * @param id account id
     * @return succeed created account
     */
    @Override
    public Account getAccount(long id) {
        return null;
    }

    /**
     * Update account by given id and Account
     *
     * @param id      Account ID
     * @param account Account updated information
     * @return succeed updated account
     */
    @Override
    public Account updateAccount(long id, Account account) {
        return null;
    }

    /**
     * List all account
     *
     * @return List Account
     */
    @Override
    public List<Account> listAllAccount() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
        };
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("account.json");
        try {
            List<Account> accounts = mapper.readValue(inputStream, typeReference);
            return accounts;
        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    /**
     * Create new account
     *
     * @param account   Account information to created
     * @param ucBuilder UriComponentsBuilder
     * @return
     */
    @Override
    public Account createAccount(Account account, UriComponentsBuilder ucBuilder) {
        return null;
    }
}
