package com.musicplayer.playlistservice.services;


import com.musicplayer.playlistservice.exceptions.PlaylistNotFoundException;
import com.musicplayer.playlistservice.models.Playlist;
import com.musicplayer.playlistservice.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    @Value("${playlistLimit}")
    private int playlistLimit;

    @Value("${songLimit}")
    private int songLimit;

    public int getPlaylistLimit() {
        return playlistLimit;
    }

    public void setPlaylistLimit(int playlistLimit) {
        this.playlistLimit = playlistLimit;
    }

    public int getSongLimit() {
        return songLimit;
    }

    public void setSongLimit(int songLimit) {
        this.songLimit = songLimit;
    }


    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }

    public Playlist getPlaylistByUserID(Integer id) throws PlaylistNotFoundException {
        Playlist playlist = playlistRepository.findById(id).orElse(null);

        if(playlist == null){
            throw new PlaylistNotFoundException();
        }
        return playlist;
    }

    public void savePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }
}
