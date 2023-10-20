package ru.almaz.caravelletravelsreborn.dto;

public class EmailMessageDTO {
    private final String email;
    private final String subject;
    private final String token;

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getToken() {
        return token;
    }

    public EmailMessageDTO(String email, String subject, String token) {
        this.email = email;
        this.subject = subject;
        this.token = token;
    }
}
