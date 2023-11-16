package ru.almaz.caravelletravelsreborn.springdbtextprovider.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exceptions_texts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionText {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exception_id", referencedColumnName = "id")
    private Exception exception;

    @Column(name = "text")
    private String text;

    public ExceptionText(Exception exception, String text) {
        this.exception = exception;
        this.text = text;
    }
}
