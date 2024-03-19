package ru.bmsalikhov.vktestapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.bmsalikhov.vktestapp.models.Log;
import ru.bmsalikhov.vktestapp.models.postsdto.CommentDto;
import ru.bmsalikhov.vktestapp.models.postsdto.PostDto;
import ru.bmsalikhov.vktestapp.services.JsonPlaceHolderPostsService;
import ru.bmsalikhov.vktestapp.services.LogsService;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostsController {

    private final JsonPlaceHolderPostsService jsonPlaceHolderPostsService;
    private final LogsService logsService;

    @GetMapping
    public ResponseEntity<PostDto[]> getAllPosts(@AuthenticationPrincipal UserDetails userDetails,
                                                 HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return ResponseEntity.ok(jsonPlaceHolderPostsService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable String id,
                                           HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return ResponseEntity.ok(jsonPlaceHolderPostsService.getPost(id));
    }

    @PostMapping
    public PostDto addPost(@AuthenticationPrincipal UserDetails userDetails,
                           @RequestBody PostDto postDto,
                           HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return jsonPlaceHolderPostsService.addPost(postDto);
    }

    @PutMapping("/{id}")
    public int putPost(@AuthenticationPrincipal UserDetails userDetails,
                       @PathVariable String id,
                       @RequestBody PostDto postDto,
                       HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return jsonPlaceHolderPostsService.putPost(postDto, id);
    }

    @DeleteMapping("/{id}")
    public int deletePost(@AuthenticationPrincipal UserDetails userDetails,
                          @PathVariable String id,
                          HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return jsonPlaceHolderPostsService.deletePost(id);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentDto[]> getPostComments(@AuthenticationPrincipal UserDetails userDetails,
                                                        @PathVariable String id,
                                                        HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return ResponseEntity.ok(jsonPlaceHolderPostsService.getPostComments(id));
    }
}
