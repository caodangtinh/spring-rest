package com.tinhcao.service;

import com.tinhcao.model.Account;
import com.tinhcao.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Get Account detail by account id
     *
     * @param id account id
     * @return succeed created account
     */
    @Override
    public Account getAccount(long id) {
        return accountRepository.getAccount(id);
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
        return accountRepository.updateAccount(id, account);
    }

    /**
     * List all account
     *
     * @return List Account
     */
    @Override
    public List<Account> listAllAccount() {
        return accountRepository.listAllAccount();
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
        return accountRepository.createAccount(account, ucBuilder);
    }
}
