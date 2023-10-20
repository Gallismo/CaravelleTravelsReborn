package ru.almaz.caravelletravelsreborn.domain.entities.user;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CredentialReset {
    private Long id;
    private Long userId;
    private String token;
    private CredentialResetType type;
    private Date creationDate;
    private Date expiresDate;

    public CredentialReset(Long id, Long userId, String token, CredentialResetType type) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.type = type;
        this.creationDate = new Date();
        Calendar expire = new GregorianCalendar();
        expire.add(Calendar.HOUR, 2);
        this.expiresDate = expire.getTime();
    }

    public boolean isExpired() {
        return new Date().after(expiresDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CredentialResetType getType() {
        return type;
    }

    public void setType(CredentialResetType type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    public enum CredentialResetType {
        EMAIL, PASSWORD;
    }
}
