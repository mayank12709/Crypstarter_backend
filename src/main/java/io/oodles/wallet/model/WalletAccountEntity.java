package io.oodles.wallet.model;

import org.springframework.stereotype.Component;

@Component
public class WalletAccountEntity {
    private String account_name;
    private String mnemonic;
    private boolean status;

    public WalletAccountEntity() {
    }
    public WalletAccountEntity(boolean status) {
        this.status = status;
    }
    public WalletAccountEntity(String account_name, String mnemonic) {
        this.account_name = account_name;
        this.mnemonic = mnemonic;
    }

    public WalletAccountEntity(String account_name, String mnemonic, boolean status) {
        this.account_name = account_name;
        this.mnemonic = mnemonic;
        this.status = status;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WalletAccountEntity{" +
                "account_name='" + account_name + '\'' +
                ", mnemonic='" + mnemonic + '\'' +
                ", status=" + status +
                '}';
    }
}

