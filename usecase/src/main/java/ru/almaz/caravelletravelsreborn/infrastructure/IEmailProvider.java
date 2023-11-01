package ru.almaz.caravelletravelsreborn.infrastructure;

import ru.almaz.caravelletravelsreborn.dto.EmailResetMessageDTO;

public interface IEmailProvider {
    void sendResetMessage(EmailResetMessageDTO messageDTO);
}
