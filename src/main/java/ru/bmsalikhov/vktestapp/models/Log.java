package ru.bmsalikhov.vktestapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "LOGS")
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;

    @Size(max = 255)
    @Column(name = "URL")
    private String url;

    @Size(max = 255)
    @Column(name = "RESULT")
    private String result;

    @Column(name = "DATE")
    private Instant date;

    public Log(String username, String url, String result) {
        this.username = username;
        this.url = url;
        this.result = result;
        this.date = Instant.now();
    }

}