package com.tinhcao.service;

import com.tinhcao.model.Account;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface AccountService {

    /**
     * Get Account detail by account id
     *
     * @param id account id
     * @return succeed created account
     */
    Account getAccount(long id);

    /**
     * Update account by given id and Account
     *
     * @param id      Account ID
     * @param account Account updated information
     * @return succeed updated account
     */
    Account updateAccount(long id, Account account);

    /**
     * List all account
     *
     * @return List Account
     */
    List<Account> listAllAccount();

    /**
     * Create new account
     *
     * @param account   Account information to created
     * @param ucBuilder UriComponentsBuilder
     * @return
     */
    Account createAccount(Account account, UriComponentsBuilder ucBuilder);
}
