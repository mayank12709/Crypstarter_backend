package io.oodles.wallet.services;

import io.oodles.wallet.dto.AccountShowDTO;
import io.oodles.wallet.model.StakingEntity;
import io.oodles.wallet.model.WalletAccountEntity;

import java.io.IOException;

public interface AccountMnemonicService {
    public WalletAccountEntity createAccount(String account_name) throws IOException, InterruptedException;

    public WalletAccountEntity importAccount(String account_name, String mnemonic) throws IOException, InterruptedException;

    public AccountShowDTO showAccount(String ac_name) throws IOException, InterruptedException;

    public String stakingAmount(StakingEntity stakingEntity) throws IOException, InterruptedException;
}
