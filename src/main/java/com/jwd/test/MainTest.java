package com.jwd.test;

import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        User u1 = new User(1, "qwe", new Address("wow", 1234));
        User u2 = (User) u1.clone();

        System.out.println("u1=" + u1);
        System.out.println("u2=" + u2);
        System.out.println();
        u2.setId(0);
        u2.setName("zxc");
        u2.getAddress().setCity("qaq");
        u2.getAddress().setPostCode(999);
        System.out.println();
        System.out.println("u2=" + u2);
        System.out.println("u1=" + u1);
        System.out.println("-------------------------------------------");
        User[] users = {u1, u2};
        User[] us = users.clone();

        System.out.println("users " + Arrays.toString(users));
        System.out.println("us    " + Arrays.toString(us));

        users[0].setName(users[0].getName() + "_Changed");
        users[0].getAddress().setCity(users[0].getAddress().getCity() + "_Changed");

        System.out.println("users " + Arrays.toString(users));
        System.out.println("us    " + Arrays.toString(us));


    }
}
