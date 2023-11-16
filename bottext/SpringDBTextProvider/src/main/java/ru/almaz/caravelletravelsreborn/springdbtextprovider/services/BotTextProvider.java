package ru.almaz.caravelletravelsreborn.springdbtextprovider.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotCommands;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotExceptions;
import ru.almaz.caravelletravelsreborn.presentation.exceptions.TextsNotFoundException;
import ru.almaz.caravelletravelsreborn.presentation.infrastructure.IBotTextProvider;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.Command;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.CustomMessage;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.Exception;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories.CommandRepository;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories.CustomMessageRepository;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories.ExceptionRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BotTextProvider implements IBotTextProvider {

    private final CommandRepository commandRepository;
    private final ExceptionRepository exceptionRepository;
    private final CustomMessageRepository customMessageRepository;

    @Autowired
    public BotTextProvider(CommandRepository commandRepository, ExceptionRepository exceptionRepository, CustomMessageRepository customMessageRepository) {
        this.commandRepository = commandRepository;
        this.exceptionRepository = exceptionRepository;
        this.customMessageRepository = customMessageRepository;
    }

    @Override
    public List<String> get(Long customId) {
        return customMessageRepository.findById(customId)
                .orElseThrow(() -> new TextsNotFoundException("Texts not found")).textsToString();
    }

    @Override
    public Map<Long, String> findAllCustomByTag(String tag) {
        return customMessageRepository.findAllByTag(tag)
                .stream().collect(Collectors.toMap(CustomMessage::getId, CustomMessage::getHeader));
    }

    @Override
    public List<String> get(BotCommands command) {
        Command dbCommand = commandRepository.findByCommand(command)
                .orElse(commandRepository.save(new Command(command)));
        return dbCommand.textsToString();
    }

    @Override
    public List<String> get(BotExceptions exception) {
        Exception dbException = exceptionRepository.findByException(exception)
                .orElse(exceptionRepository.save(new Exception(exception)));
        return dbException.textsToString();
    }
}
