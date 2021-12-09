package io.oodles.wallet.controller;

import io.oodles.wallet.model.WalletAccountEntity;
import io.oodles.wallet.services.AccountMnemonicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/common/api/v1")
public class AccountController {

    @Autowired
    private AccountMnemonicService accountMnemonicService;

    @RequestMapping(path = "/createwalletccountname/{account_name}", method = RequestMethod.POST,produces = {"application/json"})
    public ResponseEntity<WalletAccountEntity> createAccount(@PathVariable("account_name") String ac_name) throws IOException, InterruptedException {
       return new ResponseEntity<WalletAccountEntity>(accountMnemonicService.createAccount(ac_name), HttpStatus.OK);
    }

    @RequestMapping(path = "/importaccountname/", method = RequestMethod.POST, produces = MediaType.ALL_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> importAccount(@RequestBody WalletAccountEntity walletAccount) throws IOException, InterruptedException {
        return new ResponseEntity<Object>(accountMnemonicService.importAccount(walletAccount.getAccount_name(),walletAccount.getMnemonic()), HttpStatus.OK);
    }

    @RequestMapping(path = "/accountlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> importAccount() throws IOException, InterruptedException {
        return new ResponseEntity<Object>(accountMnemonicService.showAccount(), HttpStatus.OK);
    }



}
