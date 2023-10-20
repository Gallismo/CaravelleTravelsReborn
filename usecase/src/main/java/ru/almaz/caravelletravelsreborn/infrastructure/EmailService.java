package ru.almaz.caravelletravelsreborn.infrastructure;

import ru.almaz.caravelletravelsreborn.dto.EmailMessageDTO;

public interface EmailService {
    // TODO: 13.10.2023  
    void sendEmail(EmailMessageDTO messageDTO);
}
