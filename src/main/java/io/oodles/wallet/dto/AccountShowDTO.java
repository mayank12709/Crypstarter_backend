package io.oodles.wallet.dto;

public class AccountShowDTO {
    private String name;
    private String address;
    private String public_key;

    public AccountShowDTO() {
    }

    public AccountShowDTO(String name, String address, String public_key) {
        this.name = name;
        this.address = address;
        this.public_key = public_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    @Override
    public String toString() {
        return "AccountShowDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", public_key='" + public_key + '\'' +
                '}';
    }
}
