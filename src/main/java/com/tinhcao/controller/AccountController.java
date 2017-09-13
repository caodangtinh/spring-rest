package com.tinhcao.controller;

import com.tinhcao.exception.AccountException;
import com.tinhcao.model.Account;
import com.tinhcao.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "accountController")
@RequestMapping(value = "/v1/accounts/account")
public class AccountController {

    public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping(value = {""})
    @ResponseBody
    public ResponseEntity<List<Account>> getListAccount() {
        List<Account> accounts = accountService.listAllAccount();
        if (accounts.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(value = {"/account_id"})
    @ResponseBody
    public ResponseEntity<?> getAccount(@PathVariable("id") long id) {
        logger.info("Fetching Account with id {}", id);
        Account account = accountService.getAccount(id);
        if (account == null) {
            logger.error("Account with id {} not found.", id);
            return new ResponseEntity(new AccountException("User with id " + id + "not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
