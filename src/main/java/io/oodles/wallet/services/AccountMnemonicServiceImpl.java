package io.oodles.wallet.services;

import io.oodles.wallet.dto.AccountShowDTO;
import io.oodles.wallet.model.StakingEntity;
import io.oodles.wallet.model.WalletAccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountMnemonicServiceImpl  implements  AccountMnemonicService{

    Logger logger = LoggerFactory.getLogger(AccountMnemonicServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    public BufferedReader commonLogicImplementation(String cmd) throws IOException, InterruptedException{
        logger.debug("Inside commonLogicImplementation {}", cmd);
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        return buf;
    }

    @Override
    public WalletAccountEntity createAccount(String account_name) throws IOException, InterruptedException{
        logger.debug("Inside createAccountService {}", account_name);
        String  cmd = "starport account create "+ account_name;
        BufferedReader br = commonLogicImplementation(cmd);
        String line = "", menmonic = "";
        while ((line=br.readLine())!=null) {
            menmonic += line;
        }
        if(menmonic!= null)
            return new WalletAccountEntity(account_name, menmonic.substring(menmonic.lastIndexOf(":") + 1), true);
        else{
            return new WalletAccountEntity(false);
        }
    }

    @Override
    public WalletAccountEntity importAccount(String account_name, String mnemonic) throws IOException, InterruptedException {
        System.out.println(mnemonic);
        String cmd = "starport account import "+ account_name + " --secret " + mnemonic;
        BufferedReader br = commonLogicImplementation(cmd);
        String line = "", menmonic = "";
        while ((line=br.readLine())!=null) {
            menmonic += line;
        }
        System.out.println("Hii.."+menmonic);
        return null;
    }

    @Override
    public AccountShowDTO showAccount(String ac_name)throws IOException, InterruptedException {
        logger.debug("Inside showAccount {} ", ac_name);
        List<String> list = new LinkedList<>();
        String  cmd = "starport account show "+ac_name +" --address-prefix cst";
        BufferedReader br = commonLogicImplementation(cmd);
        boolean skipOddLine = true;
        String line;
        while ((line = br.readLine()) != null) {
            if (skipOddLine = !skipOddLine) {
                list = Arrays.stream(line.split("\\s+")).collect(Collectors.toList());
            }
        }
        System.out.println(list.get(1));
        String uri = "http://api.crypstarter.network/cosmos/bank/v1beta1/balances/"+list.get(1);
        String ob = restTemplate.getForObject(uri, String.class);
        return new AccountShowDTO(list.get(0), list.get(1), list.get(2), ob);
    }

    @Override
    public String stakingAmount(StakingEntity stakingEntity) throws IOException, InterruptedException {
        logger.debug("Staking : {}" , "stakingAmount Impl called");
        String cmd = "crypstarterd tx staking delegate "+ stakingEntity.getDelvaloper_address()+" "+ stakingEntity.getStaking_amount()+" --from=$(crypstarterd keys show -a + "+stakingEntity.getFrom_key_name()+") --gas="+stakingEntity.getGas()+" --gas-prices="+stakingEntity.getGas_price() +"CST --yes";
        BufferedReader br = commonLogicImplementation(cmd);
        String line = "", response = "";
        while ((line=br.readLine())!=null) {
            response += line;
        }
        return "{\n"+response+ "\n}";
    }
}
