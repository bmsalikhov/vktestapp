package ru.bmsalikhov.vktestapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.bmsalikhov.vktestapp.models.Log;
import ru.bmsalikhov.vktestapp.models.postsdto.CommentDto;
import ru.bmsalikhov.vktestapp.models.postsdto.PostDto;
import ru.bmsalikhov.vktestapp.services.JsonPlaceHolderPostsService;
import ru.bmsalikhov.vktestapp.services.LogsService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostsController {

    private final JsonPlaceHolderPostsService jsonPlaceHolderPostsService;
    private final LogsService logsService;

    @GetMapping
    public ResponseEntity<PostDto[]> getAllPosts(@AuthenticationPrincipal UserDetails userDetails) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts", "no access"));
            return new ResponseEntity<>(HttpStatusCode.valueOf(403));
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts", "successful"));
        return ResponseEntity.ok(jsonPlaceHolderPostsService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts/" + id, "no access"));
            return new ResponseEntity<>(HttpStatusCode.valueOf(403));
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts" + id, "successful"));
        return ResponseEntity.ok(jsonPlaceHolderPostsService.getPost(id));
    }

    @PostMapping
    public PostDto addPost(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PostDto postDto) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts", "no access"));
            return null;
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts", "successful"));
        return jsonPlaceHolderPostsService.addPost(postDto);
    }

    @PutMapping("/{id}")
    public int putPost(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id, @RequestBody PostDto postDto) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts/" + id, "no access"));
            return 403;
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts/" + id, "successful"));
        return jsonPlaceHolderPostsService.putPost(postDto, id);
    }

    @DeleteMapping("/{id}")
    public int deletePost(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts/" + id, "no access"));
            return 403;
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts/" + id, "successful"));
        return jsonPlaceHolderPostsService.deletePost(id);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentDto[]> getPostComments(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id) {
        if (!checkRole(userDetails)) {
            logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts/" + id + "/comments", "no access"));
            return new ResponseEntity<>(HttpStatusCode.valueOf(403));
        }
        logsService.saveLog(new Log(userDetails.getUsername(), "/api/posts/" + id + "/comments", "successful"));
        return ResponseEntity.ok(jsonPlaceHolderPostsService.getPostComments(id));
    }

    private boolean checkRole(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return (roles.contains("ROLE_ADMIN") || roles.contains("ROLE_POSTS"));
    }
}
