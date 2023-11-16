package ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotExceptions;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.Exception;

import java.util.Optional;

public interface ExceptionRepository extends JpaRepository<Exception, Long> {
    Optional<Exception> findByException(BotExceptions exception);
}
