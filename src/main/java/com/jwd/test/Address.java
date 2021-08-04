package com.jwd.test;

import java.util.Objects;

public class Address implements Cloneable {
    private String city;
    private int postCode;

    public Address() {
    }

    public Address(String city, int postCode) {
        this.city = city;
        this.postCode = postCode;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        final Address address = new Address();
        address.setCity(city);
        address.setPostCode(postCode);
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return postCode == address.postCode && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, postCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", postCode=" + postCode +
                '}';
    }
}
