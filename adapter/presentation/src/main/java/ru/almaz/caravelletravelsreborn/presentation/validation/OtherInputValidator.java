package ru.almaz.caravelletravelsreborn.presentation.validation;

import ru.almaz.caravelletravelsreborn.presentation.exceptions.OtherInputValidationException;

public class OtherInputValidator {
    private static final String ID_REGEX = "\\d+";

    public static void validateId(String id) {
        if (!id.matches(ID_REGEX)) {
            throw new OtherInputValidationException("ID is not Long", OtherInputValidationException.Reason.ID);
        }
    }
}
