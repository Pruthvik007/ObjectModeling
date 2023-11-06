package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.Song;

public class SongRepository {

    private Map<Long, Song> songs;

    public SongRepository() {
        songs = new HashMap<>();
    }

    public SongRepository(Map<Long, Song> songs) {
        this.songs = songs;
    }

    private Long generateSongId() {
        return songs.size() + 1l;
    }

    public Song createOrUpdateSong(Song song) {
        if (song.getId() == null) {
            song.setId(generateSongId());
        }
        songs.put(song.getId(), song);
        return song;
    }

    public List<Song> addOrUpdateSongs(List<Song> songs) {
        List<Song> newSongs = new ArrayList<>();
        for (Song song : songs) {
            newSongs.add(createOrUpdateSong(song));
        }
        return newSongs;
    }

    public Song getSong(Long songId) {
        return songs.getOrDefault(songId, null);
    }

    public void deleteSong(Long songId) {
        songs.remove(songId);
    }
}
