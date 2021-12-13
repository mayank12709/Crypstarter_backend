package io.oodles.wallet.controller;

import io.oodles.wallet.dto.AccountShowDTO;
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
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private AccountMnemonicService accountMnemonicService;

    @RequestMapping(path = "/createwalletccountname/{account_name}", method = RequestMethod.POST,produces = {"application/json"})
    public ResponseEntity<WalletAccountEntity> createAccount(@PathVariable("account_name") String ac_name) throws IOException, InterruptedException {
       return new ResponseEntity<WalletAccountEntity>(accountMnemonicService.createAccount(ac_name), HttpStatus.OK);
    }

    @RequestMapping(path = "/importaccountname")
    public ResponseEntity<WalletAccountEntity> importAccount(WalletAccountEntity walletAccount) throws IOException, InterruptedException {

        String s = "boil artwork wide later fee vivid view crisp choose inflict victory rabbit bind genre boy spot habit ship update nephewboil artwork wide later fee vivid view strategy creek beauty laundry crisp choose inflict victory rabbit bind genre boy spot habit ship update nephew";
        return new ResponseEntity<WalletAccountEntity>(accountMnemonicService.importAccount("mayankpex",s), HttpStatus.OK);
//        return new ResponseEntity<WalletAccountEntity>(accountMnemonicService.importAccount(walletAccount.getAccount_name(),walletAccount.getMnemonic()), HttpStatus.OK);
    }

    @RequestMapping(path = "/accountshow/{account_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountShowDTO> importAccount(@PathVariable("account_name") String ac_name) throws IOException, InterruptedException {
        return new ResponseEntity<AccountShowDTO>(accountMnemonicService.showAccount(ac_name), HttpStatus.OK);
    }



}
