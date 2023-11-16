package ru.almaz.caravelletravelsreborn.presentation.dto.output;

import java.util.Map;

public class QuestionsDTO {
    public BotAnswerDTO answer = new BotAnswerDTO();
    public Map<Long, String> questions;
}
