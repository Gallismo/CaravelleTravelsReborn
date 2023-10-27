package ru.almaz.caravelletravelsreborn.infrastructure;

public interface IPasswordEncoder {
    String encode(String password);
    boolean isEquals(String password, String encodedPassword);
}
