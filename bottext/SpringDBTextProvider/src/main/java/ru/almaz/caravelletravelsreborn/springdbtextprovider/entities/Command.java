package ru.almaz.caravelletravelsreborn.springdbtextprovider.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.almaz.caravelletravelsreborn.presentation.bot.BotCommands;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commands")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Command {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "command_name", unique = true)
    private BotCommands command;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<CommandText> texts;

    public List<String> textsToString() {
        List<String> result = new ArrayList<>();
        if (getTexts().size() > 0) {
            getTexts().forEach(commandText -> result.add(commandText.getText()));
        } else {
            result.add(command.name());
        }
        return result;
    }

    public Command(BotCommands command) {
        this.command = command;
    }
}
