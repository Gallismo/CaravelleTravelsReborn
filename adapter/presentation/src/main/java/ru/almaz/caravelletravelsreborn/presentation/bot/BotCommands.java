package ru.almaz.caravelletravelsreborn.presentation.bot;

public enum BotCommands {
    START,
    INFORMATION,
    QUESTIONS("question"),
    CREATE_BOOKING,
    FIll_BOOKING,
    COMMIT_BOOKING,
    PROCESS_BOOKING,
    CANCEL_BOOKING;

    public String tag = null;

    BotCommands() {}

    BotCommands(String tag) {
        this.tag = tag;
    }
}
