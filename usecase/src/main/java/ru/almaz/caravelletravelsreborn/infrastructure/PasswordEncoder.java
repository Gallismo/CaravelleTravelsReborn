package ru.almaz.caravelletravelsreborn.infrastructure;

public interface PasswordEncoder {
    String encode(String password);
    boolean isEquals(String password, String encodedPassword);
}
