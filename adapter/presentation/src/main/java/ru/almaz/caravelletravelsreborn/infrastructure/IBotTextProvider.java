package ru.almaz.caravelletravelsreborn.infrastructure;

import ru.almaz.caravelletravelsreborn.bot.BotCommands;
import ru.almaz.caravelletravelsreborn.bot.BotExceptions;

import java.util.List;
import java.util.Map;

public interface IBotTextProvider {
    // 1 table
    // Long id, String tag, String header, String text
    //
    // Tag for finding all for tagged command(questions)
    // UniteKey for getting all texts for 1 question
    // Header for show question header
    List<String> get(String customKey);
    // finding by tag all keys and headers
    Map<String, String> findAllCustomByTag(String tag);

    // 2 table
    // Long id, BotCommand (tag, key for finging all texts for command), String text
    List<String> get(BotCommands command);

    // 3 table
    // Long id, BotException (tag, key for finding all texts for exception), String text
    List<String> get(BotExceptions exception);

}
