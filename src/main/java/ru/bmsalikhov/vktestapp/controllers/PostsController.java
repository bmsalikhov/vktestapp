package ru.bmsalikhov.vktestapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bmsalikhov.vktestapp.models.postsdto.CommentDto;
import ru.bmsalikhov.vktestapp.models.postsdto.PostDto;
import ru.bmsalikhov.vktestapp.services.JsonPlaceHolderPostsService;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostsController {

    private final JsonPlaceHolderPostsService jsonPlaceHolderPostsService;

    @GetMapping
    public ResponseEntity<PostDto[]> getAllPosts() {
        return ResponseEntity.ok(jsonPlaceHolderPostsService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable String id) {
        return ResponseEntity.ok(jsonPlaceHolderPostsService.getPost(id));
    }

    @PostMapping
    public PostDto addPost(@RequestBody PostDto postDto) {
        return jsonPlaceHolderPostsService.addPost(postDto);
    }

    @PutMapping("/{id}")
    public int putPost(@PathVariable String id, @RequestBody PostDto postDto) {
        return jsonPlaceHolderPostsService.putPost(postDto, id);
    }

    @DeleteMapping("/{id}")
    public int deletePost(@PathVariable String id) {
        return jsonPlaceHolderPostsService.deletePost(id);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentDto[]> getPostComments(@PathVariable String id) {
        return ResponseEntity.ok(jsonPlaceHolderPostsService.getPostComments(id));
    }
}
