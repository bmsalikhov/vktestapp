package ru.bmsalikhov.vktestapp.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bmsalikhov.vktestapp.models.albumsdto.AlbumDto;
import ru.bmsalikhov.vktestapp.models.albumsdto.PhotoDto;
import ru.bmsalikhov.vktestapp.services.JsonPlaceHolderAlbumsService;

@RestController
@RequestMapping("/api/albums")
@AllArgsConstructor
public class AlbumsController {

    private final JsonPlaceHolderAlbumsService jsonPlaceHolderAlbumsService;

    @GetMapping
    public ResponseEntity<AlbumDto[]> getAllAlbums() {
        return ResponseEntity.ok(jsonPlaceHolderAlbumsService.getAllAlbums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable String id) {
        return ResponseEntity.ok(jsonPlaceHolderAlbumsService.getAlbum(id));
    }

    @PostMapping
    public AlbumDto addAlbum(@RequestBody AlbumDto albumDto) {
        return jsonPlaceHolderAlbumsService.addAlbum(albumDto);
    }

    @PutMapping("/{id}")
    public int putAlbum(@PathVariable String id, @RequestBody AlbumDto albumDto) {
        return jsonPlaceHolderAlbumsService.putAlbum(albumDto, id);
    }

    @DeleteMapping("/{id}")
    public int deleteAlbum(@PathVariable String id) {
        return jsonPlaceHolderAlbumsService.deleteAlbum(id);
    }

    @GetMapping("/{id}/photos")
    public ResponseEntity<PhotoDto[]> getAlbumPhotos(@PathVariable String id) {
        return ResponseEntity.ok(jsonPlaceHolderAlbumsService.getAlbumPhotos(id));
    }
}
