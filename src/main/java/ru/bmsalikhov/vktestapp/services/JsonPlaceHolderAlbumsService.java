package ru.bmsalikhov.vktestapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bmsalikhov.vktestapp.models.albumsdto.AlbumDto;
import ru.bmsalikhov.vktestapp.models.albumsdto.PhotoDto;

import java.net.URI;

@Service
public class JsonPlaceHolderAlbumsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String jphApi = "https://jsonplaceholder.typicode.com/albums/";

    public AlbumDto[] getAllAlbums() {
        return restTemplate.getForObject(jphApi, AlbumDto[].class);
    }

    public AlbumDto getAlbum(String id) {
        String url = jphApi + id;
        return restTemplate.getForObject(url, AlbumDto.class);
    }

    @SneakyThrows
    public AlbumDto addAlbum(AlbumDto albumDto) {
        return restTemplate.postForObject(new URI(jphApi), albumDto, AlbumDto.class);
    }
    @SneakyThrows
    public int putAlbum(AlbumDto albumDto, String id) {
        String url = jphApi + id;
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonAlbumDto = objectWriter.writeValueAsString(albumDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonAlbumDto, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
        return responseEntity.getStatusCode().value();
    }

    @SneakyThrows
    public int deleteAlbum(String id) {
        String url = jphApi + id;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), String.class);
        return responseEntity.getStatusCode().value();
    }

    public PhotoDto[] getAlbumPhotos(String id) {
        String url = jphApi + id + "/photos";
        return restTemplate.getForObject(url, PhotoDto[].class);
    }
}
