package ru.almaz.caravelletravelsreborn.bot;

import java.util.List;

public interface IBotTextProvider {
    List<String> get(String customKey);
    List<String> get(BotCommands command);
    List<String> get(BotExceptions exception);
}
