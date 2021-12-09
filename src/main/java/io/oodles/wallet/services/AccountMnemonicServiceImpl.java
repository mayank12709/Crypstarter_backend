package io.oodles.wallet.services;

import io.oodles.wallet.dto.AccountShowDTO;
import io.oodles.wallet.model.WalletAccountEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountMnemonicServiceImpl  implements  AccountMnemonicService{

    public BufferedReader commonLogicImplementation(String cmd) throws IOException, InterruptedException{
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        return buf;
    }

    @Override
    public WalletAccountEntity createAccount(String account_name) throws IOException, InterruptedException{
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
        List<String> list = new LinkedList<>();
        String  cmd = "starport account show "+ac_name;
        BufferedReader br = commonLogicImplementation(cmd);
        boolean skipOddLine = true;
        String line;
        while ((line = br.readLine()) != null) {
            if (skipOddLine = !skipOddLine) {
                list = Arrays.stream(line.split("\\s+")).collect(Collectors.toList());
            }
        }
        return new AccountShowDTO(list.get(0), list.get(1), list.get(2));
    }
}
