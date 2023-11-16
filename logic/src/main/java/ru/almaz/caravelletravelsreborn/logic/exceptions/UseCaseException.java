package ru.almaz.caravelletravelsreborn.logic.exceptions;

public abstract class UseCaseException extends RuntimeException {
    public UseCaseException(String message) {
        super(message);
    }
}
