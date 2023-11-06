package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.crio.jukebox.entities.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SongRepositoryTest {

    private SongRepository songRepository;

    @BeforeEach
    public void setUp() {
        Map<Long, Song> songs = new HashMap<>();
        songs.put(1l, new Song(1l, "song1", "genre1","album1",new ArrayList<String>(){
            {
                add("artist1");
            }
        }));
        songs.put(2l, new Song(2l, "song2", "genre2","album2",new ArrayList<String>(){
            {
                add("artist2");
            }
        }));
        songs.put(3l, new Song(3l, "song3", "genre3","album3",new ArrayList<String>(){
            {
                add("artist3");
            }
        }));
        songRepository = new SongRepository(songs);
    }

    @Test
    @DisplayName("Testing Song Creation")
    public void addSongTest() {
        Song actualSong = new Song(4l, "song4", "genre4","album4",new ArrayList<String>(){
            {
                add("artist4");
            }
        });
        Song expectedSong = songRepository.createOrUpdateSong(new Song("song4", "genre4","album4",new ArrayList<String>(){
            {
                add("artist4");
            }
        }));
        Assertions.assertEquals(expectedSong, actualSong);
    }
}
