package io.oodles.wallet.services;

import io.oodles.wallet.model.WalletAccountEntity;

import java.io.IOException;

public interface AccountMnemonicService {
    public WalletAccountEntity createAccount(String account_name) throws IOException, InterruptedException;

    Object importAccount(String account_name, String mnemonic) throws IOException, InterruptedException;

    Object showAccount() throws IOException, InterruptedException;
}
