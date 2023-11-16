package ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.CommandText;

public interface CommandTextRepository extends JpaRepository<CommandText, Long> {
}
