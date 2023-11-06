package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.Map;
import com.crio.jukebox.entities.Playlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaylistRepositoryTest {
    private PlaylistRepository playlistRepository;

    @BeforeEach
    public void setUp() {
        Map<Long, Playlist> playlists = new HashMap<>();
        playlists.put(1l, new Playlist(1l, "playlist1", 1l, null));
        playlistRepository = new PlaylistRepository(playlists);
    }

    @Test
    @DisplayName("Testing Playlist Creation")
    public void addPlaylistTest() {
        Playlist expectedPlaylist = new Playlist(2l, "playlist2", 2l, null);
        Playlist actualPlaylist =
                playlistRepository.createOrUpdatePlaylist(new Playlist("playlist2", 2l, null));
        Assertions.assertEquals(expectedPlaylist, actualPlaylist);
    }
}
