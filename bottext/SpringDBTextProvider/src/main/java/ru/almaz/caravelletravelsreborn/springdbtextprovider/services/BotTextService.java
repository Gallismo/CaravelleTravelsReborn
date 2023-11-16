package ru.almaz.caravelletravelsreborn.springdbtextprovider.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotCommands;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotExceptions;
import ru.almaz.caravelletravelsreborn.presentation.exceptions.TextsNotFoundException;
import ru.almaz.caravelletravelsreborn.presentation.infrastructure.IBotTextService;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.Command;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.CommandText;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.Exception;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.ExceptionText;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories.*;

import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
public class BotTextService implements IBotTextService {

    private final CommandRepository commandRepository;
    private final ExceptionRepository exceptionRepository;
    private final CustomMessageRepository customMessageRepository;

    private final CommandTextRepository commandTextRepository;
    private final ExceptionTextRepository exceptionTextRepository;
    private final CustomMessageTextRepository customMessageTextRepository;

    @Autowired
    public BotTextService(CommandRepository commandRepository, ExceptionRepository exceptionRepository, CustomMessageRepository customMessageRepository, CommandTextRepository commandTextRepository, ExceptionTextRepository exceptionTextRepository, CustomMessageTextRepository customMessageTextRepository) {
        this.commandRepository = commandRepository;
        this.exceptionRepository = exceptionRepository;
        this.customMessageRepository = customMessageRepository;
        this.commandTextRepository = commandTextRepository;
        this.exceptionTextRepository = exceptionTextRepository;
        this.customMessageTextRepository = customMessageTextRepository;
    }

    @Override
    public void create(BotCommands command) {
        commandRepository.save(new Command(command));
    }

    @Override
    public void addText(BotCommands command, String text) {
        Command dbCommand = commandRepository.findByCommand(command)
                .orElse(commandRepository.save(new Command(command)));
        CommandText commandText = new CommandText(dbCommand, text);
        dbCommand.getTexts().add(commandText);

        commandTextRepository.save(commandText);
    }

    @Override
    public Map<Long, String> findTextsByCommand(BotCommands command) {
        Command dbCommand = commandRepository.findByCommand(command)
                .orElse(commandRepository.save(new Command(command)));
        return dbCommand.getTexts().stream().collect(Collectors.toMap(CommandText::getId, CommandText::getText));
    }

    @Override
    public void deleteCommandText(Long id) {
        commandTextRepository.deleteById(id);
    }

    @Override
    public void updateCommandText(Long id, String newText) {
        CommandText commandText = commandTextRepository.findById(id).orElseThrow(() -> new TextsNotFoundException("Texts not found"));
        commandText.setText(newText);
        commandTextRepository.save(commandText);
    }

    @Override
    public void create(BotExceptions exception) {
        exceptionRepository.save(new Exception(exception));
    }

    @Override
    public void addText(BotExceptions exception, String text) {
        Exception dbException = exceptionRepository.findByException(exception)
                .orElse(exceptionRepository.save(new Exception(exception)));
        ExceptionText exceptionText = new ExceptionText(dbException, text);
        dbException.getTexts().add(exceptionText);

        exceptionTextRepository.save(exceptionText);
    }

    @Override
    public Map<Long, String> findTextsByException(BotExceptions exception) {
        Exception dbException = exceptionRepository.findByException(exception)
                .orElse(exceptionRepository.save(new Exception(exception)));
        return dbException.getTexts().stream().collect(Collectors.toMap(ExceptionText::getId, ExceptionText::getText));
    }

    @Override
    public void deleteExceptionText(Long id) {
        exceptionTextRepository.deleteById(id);
    }

    @Override
    public void updateExceptionText(Long id, String newText) {

    }

    @Override
    public void create(String tag, String header) {

    }

    @Override
    public void addText(Long customId, String text) {

    }

    @Override
    public Map<Long, String> findTextsByCustomId(Long customId) {
        return null;
    }

    @Override
    public void deleteCustomText(Long id) {

    }

    @Override
    public void updateCustomText(Long id, String newText) {

    }
}
