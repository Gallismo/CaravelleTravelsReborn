package ru.almaz.caravelletravelsreborn.presentation.exceptions;

public class OtherInputValidationException extends PresentationException {
    public final Reason reason;
    public OtherInputValidationException(String message, Reason reason) {
        super(message);
        this.reason = reason;
    }

    public enum Reason {
        ID
    }
}
