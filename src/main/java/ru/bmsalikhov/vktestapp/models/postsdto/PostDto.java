package ru.bmsalikhov.vktestapp.models.postsdto;

import lombok.Data;

@Data
public class PostDto {
    private String userId;
    private String id;
    private String title;
    private String body;
}
