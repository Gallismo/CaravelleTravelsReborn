package ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.CustomMessageText;

public interface CustomMessageTextRepository extends JpaRepository<CustomMessageText, Long> {
}
