package ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotCommands;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.Command;

import java.util.Optional;

public interface CommandRepository extends JpaRepository<Command, Long> {
    Optional<Command> findByCommand(BotCommands command);
}
