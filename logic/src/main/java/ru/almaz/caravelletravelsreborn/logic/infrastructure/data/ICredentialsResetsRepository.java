package ru.almaz.caravelletravelsreborn.logic.infrastructure.data;

import ru.almaz.caravelletravelsreborn.domain.entities.user.CredentialReset;

import java.util.Optional;

public interface ICredentialsResetsRepository {
    // TODO: 13.10.2023
    Optional<CredentialReset> findById(Long id);
    Optional<CredentialReset> findByToken(String token);
    CredentialReset save(Long id, CredentialReset reset);
}
