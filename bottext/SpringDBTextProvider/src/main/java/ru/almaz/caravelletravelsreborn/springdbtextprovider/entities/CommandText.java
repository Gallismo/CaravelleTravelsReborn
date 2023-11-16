package ru.almaz.caravelletravelsreborn.springdbtextprovider.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commands_texts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommandText {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "command_id", referencedColumnName = "id")
    private Command parent;

    @Column(name = "text")
    private String text;

    public CommandText(Command parent, String text) {
        this.parent = parent;
        this.text = text;
    }
}
