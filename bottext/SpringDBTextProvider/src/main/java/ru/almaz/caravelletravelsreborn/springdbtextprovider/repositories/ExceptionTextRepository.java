package ru.almaz.caravelletravelsreborn.springdbtextprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.almaz.caravelletravelsreborn.springdbtextprovider.entities.ExceptionText;

public interface ExceptionTextRepository extends JpaRepository<ExceptionText, Long> {
}
