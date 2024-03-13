package ru.bmsalikhov.vktestapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.bmsalikhov.vktestapp.models.Log;
import ru.bmsalikhov.vktestapp.models.postsdto.PostDto;
import ru.bmsalikhov.vktestapp.models.usersdto.UserDto;
import ru.bmsalikhov.vktestapp.services.JsonPlaceHolderUsersService;
import ru.bmsalikhov.vktestapp.services.LogsService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersController {

    private final JsonPlaceHolderUsersService jsonPlaceHolderUsersService;
    private final LogsService logsService;

    @GetMapping
    public ResponseEntity<UserDto[]> getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/users", "no access"));
            return new ResponseEntity<>(HttpStatusCode.valueOf(403));
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/users", "successful"));
        return ResponseEntity.ok(jsonPlaceHolderUsersService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/users/" + id, "no access"));
            return new ResponseEntity<>(HttpStatusCode.valueOf(403));
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/users/" + id, "successful"));
        return ResponseEntity.ok(jsonPlaceHolderUsersService.getUser(id));
    }

    @PostMapping
    public UserDto addUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserDto userDto) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/users", "no access"));
            return null;
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/users", "successful"));
        return jsonPlaceHolderUsersService.addUser(userDto);
    }

    @PutMapping("/{id}")
    public int putUser(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id, @RequestBody UserDto userDto) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/users/" + id, "no access"));
            return 403;
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/users/" + id, "successful"));
        return jsonPlaceHolderUsersService.putUser(userDto, id);
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/users/" + id, "no access"));
            return 403;
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/users/" + id, "successful"));
        return jsonPlaceHolderUsersService.deleteUser(id);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<PostDto[]> getUserPosts(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/users" + id + "/posts", "no access"));
            return new ResponseEntity<>(HttpStatusCode.valueOf(403));
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/users" + id + "/posts", "successful"));
        return ResponseEntity.ok(jsonPlaceHolderUsersService.getUserPosts(id));
    }

    private boolean checkRole(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return (roles.contains("ROLE_ADMIN") || roles.contains("ROLE_USERS"));
    }

}
