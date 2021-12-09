package io.oodles.wallet.services;

import io.oodles.wallet.model.WalletAccountEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class AccountMnemonicServiceImpl  implements  AccountMnemonicService{
    private String menmonic;

    public String commonLogicImplementation(String cmd) throws IOException, InterruptedException{
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line=buf.readLine())!=null) {
            menmonic = line;
            menmonic += line;
            System.out.println(line);
        }
        return menmonic;
    }

    @Override
    public WalletAccountEntity createAccount(String account_name) throws IOException, InterruptedException{
        String  cmd = "starport account create "+ account_name;
        String mem = commonLogicImplementation(cmd);
        if(mem!= null)
            return new WalletAccountEntity(account_name, mem, true);
        else{
            return new WalletAccountEntity(false);
        }
    }

    @Override
    public Object importAccount(String account_name, String mnemonic) throws IOException, InterruptedException {
        String cmd = "starport account import "+ account_name + " --secret" + mnemonic;
        String mem = commonLogicImplementation(cmd);
        System.out.println(mem);
        return null;
    }

    @Override
    public Object showAccount()throws IOException, InterruptedException {
        String  cmd = "starport account list";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line=buf.readLine())!=null) {
            System.out.println(line);
        }
        return null;
    }
}
