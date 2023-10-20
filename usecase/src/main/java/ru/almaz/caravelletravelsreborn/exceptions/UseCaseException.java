package ru.almaz.caravelletravelsreborn.exceptions;

public abstract class UseCaseException extends RuntimeException {
    public UseCaseException(String message) {
        super(message);
    }
}
