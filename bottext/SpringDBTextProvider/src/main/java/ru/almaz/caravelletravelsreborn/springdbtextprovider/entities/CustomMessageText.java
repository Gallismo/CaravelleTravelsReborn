package ru.almaz.caravelletravelsreborn.springdbtextprovider.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "custom_messages_texts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomMessageText {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private CustomMessage message;

    @Column(name = "text")
    private String text;
}
