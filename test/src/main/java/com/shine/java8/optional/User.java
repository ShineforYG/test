package com.shine.java8.optional;

import com.shine.java8.optional.Address;

import java.util.Optional;

public class User {

    private String  username;
    private String  password;
    private Integer age;
    private Address address;

    private Optional<Address> optAddress;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public Optional<Address> getOptAddress() {
        return optAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setOptAddress(Optional<Address> optAddress) {
        this.optAddress = optAddress;
    }
}
