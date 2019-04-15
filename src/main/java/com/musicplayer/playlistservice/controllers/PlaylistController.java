package com.musicplayer.playlistservice.controllers;

import com.musicplayer.playlistservice.exceptions.PlaylistNotFoundException;
import com.musicplayer.playlistservice.models.Playlist;
import com.musicplayer.playlistservice.models.Song;
import com.musicplayer.playlistservice.services.SongServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.musicplayer.playlistservice.services.PlaylistService;

import java.util.ArrayList;
import java.util.List;


@RestController
public class PlaylistController {


    private final PlaylistService playlistService;
    private final SongServiceProxy songServiceProxy;


    @Autowired
    public PlaylistController(PlaylistService playlistService,SongServiceProxy songServiceProxy) {
        this.playlistService = playlistService;
        this.songServiceProxy=songServiceProxy;
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

    @GetMapping("playlist-songs/{id}")
    public List<Song> getSongsInPlayList(@PathVariable int id) throws PlaylistNotFoundException {
        Playlist playlist=getPlaylistByUserID(id);
        List<Song> songs=new ArrayList<Song>();
        playlist.getSonglist().forEach(songId->songs.add(songServiceProxy.getSongById(songId)));
        System.out.println("Songs:"+songs.toArray().toString());
        return songs;
    }


}
