package com.shine.java8.optional;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("shine");
        user.setPassword("1234");
        user.setAge(22);
        user.setOptAddress(Optional.empty()); // 返回空对象
        user.setOptAddress(Optional.of(new Address("达尔文路", "88号")));

        String street = user.getOptAddress().filter(address -> address.getDoor().contains("88")).get().getStreet();
        System.out.println(street);

        user.getOptAddress()
                .filter(address -> address.getDoor().contains("88"))
                .ifPresent(System.out::println);

        user.getOptAddress()
                .filter(address -> address.getDoor().contains("123"))
                .ifPresent(System.out::println);
        System.out.println(
                user.getOptAddress().filter(address -> address.getDoor().contains("123"))
        );

        // 返回String对象
        String door = user.getOptAddress()
                .map(address -> {
                    return address.getDoor();
                })
                .orElse("没有");
        System.out.println(door);


        // 返回Optional包装的对象
        Optional<User> userOptional = Optional.of(user);
        Optional<Address> address = userOptional.flatMap(User::getOptAddress);
        System.out.println(address.get().toString());
    }
}
