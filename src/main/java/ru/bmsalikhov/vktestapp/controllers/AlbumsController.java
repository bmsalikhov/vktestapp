package ru.bmsalikhov.vktestapp.controllers;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.bmsalikhov.vktestapp.models.Log;
import ru.bmsalikhov.vktestapp.models.albumsdto.AlbumDto;
import ru.bmsalikhov.vktestapp.models.albumsdto.PhotoDto;
import ru.bmsalikhov.vktestapp.services.JsonPlaceHolderAlbumsService;
import ru.bmsalikhov.vktestapp.services.LogsService;

@RestController
@RequestMapping("/api/albums")
@AllArgsConstructor
public class AlbumsController {

    private final JsonPlaceHolderAlbumsService jsonPlaceHolderAlbumsService;
    private final LogsService logsService;

    @GetMapping
    public ResponseEntity<AlbumDto[]> getAllAlbums(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return ResponseEntity.ok(jsonPlaceHolderAlbumsService.getAllAlbums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> getAlbum(@AuthenticationPrincipal UserDetails userDetails,
                                             @PathVariable String id,
                                             HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return ResponseEntity.ok(jsonPlaceHolderAlbumsService.getAlbum(id));
    }

    @PostMapping
    public AlbumDto addAlbum(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestBody AlbumDto albumDto,
                             HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return jsonPlaceHolderAlbumsService.addAlbum(albumDto);
    }

    @PutMapping("/{id}")
    public int putAlbum(@AuthenticationPrincipal UserDetails userDetails,
                        @PathVariable String id,
                        @RequestBody AlbumDto albumDto,
                        HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return jsonPlaceHolderAlbumsService.putAlbum(albumDto, id);
    }

    @DeleteMapping("/{id}")
    public int deleteAlbum(@AuthenticationPrincipal UserDetails userDetails,
                           @PathVariable String id,
                           HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return jsonPlaceHolderAlbumsService.deleteAlbum(id);
    }

    @GetMapping("/{id}/photos")
    public ResponseEntity<PhotoDto[]> getAlbumPhotos(@AuthenticationPrincipal UserDetails userDetails,
                                                     @PathVariable String id,
                                                     HttpServletRequest request) {
        logsService.saveLog(userDetails, request, "successful");
        return ResponseEntity.ok(jsonPlaceHolderAlbumsService.getAlbumPhotos(id));
    }

}
