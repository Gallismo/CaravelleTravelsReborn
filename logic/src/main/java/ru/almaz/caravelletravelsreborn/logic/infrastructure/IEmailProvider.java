package ru.almaz.caravelletravelsreborn.logic.infrastructure;

import ru.almaz.caravelletravelsreborn.logic.dto.EmailResetMessageDTO;

public interface IEmailProvider {
    void sendResetMessage(EmailResetMessageDTO messageDTO);
}
