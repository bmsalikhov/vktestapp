package ru.bmsalikhov.vktestapp.models.albumsdto;

import lombok.Data;

@Data
public class PhotoDto {
    private String albumId;
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
