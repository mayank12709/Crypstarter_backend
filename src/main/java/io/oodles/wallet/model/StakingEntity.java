package io.oodles.wallet.model;

import org.springframework.stereotype.Component;

@Component
public class StakingEntity {

    private String delvaloper_address;
    private String staking_amount;
    private String from_key_name;
    private String gas;
    private String gas_price;

    public String getDelvaloper_address() {
        return delvaloper_address;
    }

    public void setDelvaloper_address(String delvaloper_address) {
        this.delvaloper_address = delvaloper_address;
    }

    public String getStaking_amount() {
        return staking_amount;
    }

    public void setStaking_amount(String staking_amount) {
        this.staking_amount = staking_amount;
    }

    public String getFrom_key_name() {
        return from_key_name;
    }

    public void setFrom_key_name(String from_key_name) {
        this.from_key_name = from_key_name;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getGas_price() {
        return gas_price;
    }

    public void setGas_price(String gas_price) {
        this.gas_price = gas_price;
    }

    @Override
    public String toString() {
        return "StakingEntity{" +
                "delvaloper_address='" + delvaloper_address + '\'' +
                ", staking_amount='" + staking_amount + '\'' +
                ", from_key_name='" + from_key_name + '\'' +
                ", gas='" + gas + '\'' +
                ", gas_price='" + gas_price + '\'' +
                '}';
    }
}
