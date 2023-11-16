package ru.almaz.caravelletravelsreborn.presentation.infrastructure;

import ru.almaz.caravelletravelsreborn.presentation.bot.BotCommands;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotExceptions;
import ru.almaz.caravelletravelsreborn.presentation.exceptions.TextsNotFoundException;

import java.util.List;
import java.util.Map;

public interface IBotTextProvider {
    // 1 table
    // Long ID, String tag, String header
    //
    // Tag for finding all texts for tagged command(questions)
    // ID for getting all texts for 1 question
    // Header for show question header
    List<String> get(Long customId) throws TextsNotFoundException;
    // finding by tag all keys and headers
    Map<Long, String> findAllCustomByTag(String tag);

    // 2 table
    // Long id, BotCommand (tag, key for finging all texts for command), String text
    List<String> get(BotCommands command);


    // 3 table
    // Long id, BotException (tag, key for finding all texts for exception), String text
    List<String> get(BotExceptions exception);

}
