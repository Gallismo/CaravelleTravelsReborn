package ru.almaz.caravelletravelsreborn.springdbtextprovider.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "custom_messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomMessage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag")
    private String tag;

    @Column(name = "header")
    private String header;

    @OneToMany(mappedBy = "message", fetch = FetchType.EAGER)
    private List<CustomMessageText> texts;

    public List<String> textsToString() {
        List<String> result = new ArrayList<>();
        texts.forEach(customMessageText -> result.add(customMessageText.getText()));
        if (texts.size() > 0) {
            texts.forEach(commandText -> result.add(commandText.getText()));
        } else {
            result.add("Empty texts");
        }
        return result;
    }
}
