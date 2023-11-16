package ru.almaz.caravelletravelsreborn.logic.infrastructure;

public interface IPasswordEncoder {
    String encode(String password);
    boolean isEquals(String password, String encodedPassword);
}
