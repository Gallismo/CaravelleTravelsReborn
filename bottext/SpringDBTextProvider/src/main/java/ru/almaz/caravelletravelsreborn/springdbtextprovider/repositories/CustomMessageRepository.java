package ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.CustomMessage;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.ExceptionText;

import java.util.List;
import java.util.Optional;

public interface CustomMessageRepository extends JpaRepository<CustomMessage, Long> {
    List<CustomMessage> findAllByTag(String tag);
}
