package ru.almaz.caravelletravelsreborn.infrastructure;

import ru.almaz.caravelletravelsreborn.dto.EmailResetMessageDTO;

public interface IEmailProvider {
    // TODO: 13.10.2023  
    void sendResetMessage(EmailResetMessageDTO messageDTO);
}
