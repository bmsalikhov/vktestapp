package ru.bmsalikhov.vktestapp.models.postsdto;

import lombok.Data;

@Data
public class CommentDto {
    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;
}
