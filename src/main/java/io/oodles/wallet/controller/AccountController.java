package io.oodles.wallet.controller;

import io.oodles.wallet.dto.AccountShowDTO;
import io.oodles.wallet.model.StakingEntity;
import io.oodles.wallet.model.WalletAccountEntity;
import io.oodles.wallet.services.AccountMnemonicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountMnemonicService accountMnemonicService;

    @GetMapping(path = "/createwalletccountname/{account_name}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletAccountEntity> createAccount(@PathVariable(value = "account_name" , required = false) String ac_name) throws IOException, InterruptedException {
        logger.debug("createAccountMethod {}",ac_name);
       return new ResponseEntity<WalletAccountEntity>(accountMnemonicService.createAccount(ac_name), HttpStatus.OK);
    }

    @RequestMapping(path = "/importaccountname")
    public ResponseEntity<WalletAccountEntity> importAccountMnemonics(WalletAccountEntity walletAccount) throws IOException, InterruptedException {

        String s = "boil artwork wide later fee vivid view crisp choose inflict victory rabbit bind genre boy spot habit ship update nephewboil artwork wide later fee vivid view strategy creek beauty laundry crisp choose inflict victory rabbit bind genre boy spot habit ship update nephew";
        return new ResponseEntity<WalletAccountEntity>(accountMnemonicService.importAccount("mayankpex",s), HttpStatus.OK);
//        return new ResponseEntity<WalletAccountEntity>(accountMnemonicService.importAccount(walletAccount.getAccount_name(),walletAccount.getMnemonic()), HttpStatus.OK);
    }

    @GetMapping(path = "/accountshow/{account_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountShowDTO> importAccount(@PathVariable(value = "account_name" , required = false) String ac_name) throws IOException, InterruptedException {
        logger.debug("importAccount {}",ac_name);
        return new ResponseEntity<AccountShowDTO>(accountMnemonicService.showAccount(ac_name), HttpStatus.OK);
    }

    @PostMapping(path = "/stake/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> stakingAmount(@RequestBody StakingEntity stakingEntity) throws IOException, InterruptedException {
        logger.debug("stakingAmount {}", "Inside Staking");
        return new ResponseEntity<String>(accountMnemonicService.stakingAmount(stakingEntity), HttpStatus.OK);
    }



}
