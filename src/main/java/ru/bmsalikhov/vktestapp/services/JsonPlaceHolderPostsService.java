package ru.bmsalikhov.vktestapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bmsalikhov.vktestapp.models.postsdto.CommentDto;
import ru.bmsalikhov.vktestapp.models.postsdto.PostDto;

import java.net.URI;

@Service
public class JsonPlaceHolderPostsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String jphApi = "https://jsonplaceholder.typicode.com/posts/";

    public PostDto[] getAllPosts() {
        return restTemplate.getForObject(jphApi, PostDto[].class);
    }

    public PostDto getPost(String id) {
        String url = jphApi + id;
        return restTemplate.getForObject(url, PostDto.class);
    }

    @SneakyThrows
    public PostDto addPost(PostDto postDto) {
        return restTemplate.postForObject(new URI(jphApi), postDto, PostDto.class);
    }
    @SneakyThrows
    public int putPost(PostDto postDto, String id) {
        String url = jphApi + id;
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonPostDto = objectWriter.writeValueAsString(postDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonPostDto, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
        return responseEntity.getStatusCode().value();
    }

    @SneakyThrows
    public int deletePost(String id) {
        String url = jphApi + id;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), String.class);
        return responseEntity.getStatusCode().value();
    }

    public CommentDto[] getPostComments(String id) {
        String url = jphApi + id + "/comments";
        return restTemplate.getForObject(url, CommentDto[].class);
    }
}
