package com.musicplayer.playlistservice.controllers;

import com.musicplayer.playlistservice.exceptions.PlaylistNotFoundException;
import com.musicplayer.playlistservice.models.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.musicplayer.playlistservice.services.PlaylistService;


@RestController
public class PlaylistController {


    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/")
    public String getHello(){
        return "Hello";
    }

    @GetMapping("/playlist-limit")
    public String getPlaylistLimit(){
        return "The playlistLimit="+playlistService.getPlaylistLimit();
    }

    @GetMapping("/playlists/{id}")
    private Playlist getPlaylistByUserID(@PathVariable Integer id) throws PlaylistNotFoundException {
        return playlistService.getPlaylistByUserID(id);
    }

    @PostMapping("/playlist")
    private String savePlaylist(@RequestBody Playlist playlist){
        playlistService.savePlaylist(playlist);
        return "saved";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void playlistNotFoundHandler(PlaylistNotFoundException ex){

    }


}
