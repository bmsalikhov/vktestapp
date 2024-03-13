package ru.bmsalikhov.vktestapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bmsalikhov.vktestapp.models.postsdto.PostDto;
import ru.bmsalikhov.vktestapp.models.usersdto.UserDto;
import ru.bmsalikhov.vktestapp.services.JsonPlaceHolderUsersService;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersController {

    private final JsonPlaceHolderUsersService jsonPlaceHolderUsersService;

    @GetMapping
    public ResponseEntity<UserDto[]> getAllUsers() {
        return ResponseEntity.ok(jsonPlaceHolderUsersService.getAllUsers());
    }

    //TODO вкрутить возврат 404 в случае большого ID, а то ничего не выводит
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        return ResponseEntity.ok(jsonPlaceHolderUsersService.getUser(id));
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return jsonPlaceHolderUsersService.addUser(userDto);
    }

    @PutMapping("/{id}")
    public int putUser(@PathVariable String id, @RequestBody UserDto userDto) {
        return jsonPlaceHolderUsersService.putUser(userDto, id);
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable String id) {
        return jsonPlaceHolderUsersService.deleteUser(id);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<PostDto[]> getUserPosts(@PathVariable String id) {
        return ResponseEntity.ok(jsonPlaceHolderUsersService.getUserPosts(id));
    }

}
