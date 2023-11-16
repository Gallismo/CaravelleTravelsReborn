package ru.almaz.caravelletravelsreborn.springdbtextprovider.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotExceptions;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exceptions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exception {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "exception_name", unique = true)
    private BotExceptions exception;

    @OneToMany(mappedBy = "exception", fetch = FetchType.EAGER)
    private List<ExceptionText> texts;

    public List<String> textsToString() {
        List<String> result = new ArrayList<>();
        if (texts.size() > 0) {
            texts.forEach(exceptionText -> result.add(exceptionText.getText()));
        } else {
            result.add(exception.name());
        }
        return result;
    }

    public Exception(BotExceptions exception) {
        this.exception = exception;
    }
}
