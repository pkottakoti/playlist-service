package com.musicplayer.playlistservice.services;

import com.musicplayer.playlistservice.models.Song;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="zuul-gateway-server")
@RibbonClient(name="song-service")
public interface SongServiceProxy {

    @GetMapping("song-service/song/{id}/")
    Song getSongById(@PathVariable("id") int id);
}
