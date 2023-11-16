package ru.almaz.caravelletravelsreborn.presentation.infrastructure;

import ru.almaz.caravelletravelsreborn.presentation.bot.BotCommands;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotExceptions;
import ru.almaz.caravelletravelsreborn.presentation.exceptions.TextsNotFoundException;

import java.util.Map;

public interface IBotTextService {
    // Setting texts to commands
    void create(BotCommands command);
    void addText(BotCommands command, String text);
    Map<Long, String> findTextsByCommand(BotCommands command);
    void deleteCommandText(Long id);
    void updateCommandText(Long id, String newText) throws TextsNotFoundException;

    // Setting texts to exceptions
    void create(BotExceptions exception);
    void addText(BotExceptions exception, String text);
    Map<Long, String> findTextsByException(BotExceptions exception);
    void deleteExceptionText(Long id);
    void updateExceptionText(Long id, String newText);

    // Custom texts, for example for questions
    void create(String tag, String header);
    void addText(Long customId, String text);
    Map<Long, String> findTextsByCustomId(Long customId);
    void deleteCustomText(Long id);
    void updateCustomText(Long id, String newText);
}
