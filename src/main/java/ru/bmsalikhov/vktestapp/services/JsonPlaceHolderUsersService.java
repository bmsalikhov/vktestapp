package ru.bmsalikhov.vktestapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bmsalikhov.vktestapp.models.postsdto.PostDto;
import ru.bmsalikhov.vktestapp.models.usersdto.UserDto;

import java.net.URI;

@Service
public class JsonPlaceHolderUsersService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String jphApi = "https://jsonplaceholder.typicode.com/users/";

    public UserDto[] getAllUsers() {
        return restTemplate.getForObject(jphApi, UserDto[].class);
    }

    public UserDto getUser(String id) {
        String url = jphApi + id;
        return restTemplate.getForObject(url, UserDto.class);
    }

    @SneakyThrows
    public UserDto addUser(UserDto userDto) {
        return restTemplate.postForObject(new URI(jphApi), userDto, UserDto.class);
    }
    @SneakyThrows
    public int putUser(UserDto userDto, String id) {
        String url = jphApi + id;
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonUserDto = objectWriter.writeValueAsString(userDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonUserDto, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
        return responseEntity.getStatusCode().value();
    }

    @SneakyThrows
    public int deleteUser(String id) {
        String url = jphApi + id;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), String.class);
        return responseEntity.getStatusCode().value();
    }

    public PostDto[] getUserPosts(String id) {
        String url = jphApi + id + "/posts";
        return restTemplate.getForObject(url, PostDto[].class);
    }
}
