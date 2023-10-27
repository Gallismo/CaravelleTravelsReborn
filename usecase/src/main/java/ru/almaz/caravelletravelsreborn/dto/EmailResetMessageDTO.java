package ru.almaz.caravelletravelsreborn.dto;

import ru.almaz.caravelletravelsreborn.domain.entities.user.CredentialReset;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;

public class EmailResetMessageDTO {
    private final User email;
    private final CredentialReset token;

    public User getEmail() {
        return email;
    }

    public CredentialReset getToken() {
        return token;
    }

    public EmailResetMessageDTO(User email, CredentialReset token) {
        this.email = email;
        this.token = token;
    }
}
