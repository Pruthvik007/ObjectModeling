package com.crio.jukebox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("PlayerServicesTest")
@ExtendWith(MockitoExtension.class)
public class PlayerServicesTest {
    @Mock
    private PlaylistServices playlistServicesMock;
    @InjectMocks
    private PlayerServices playerServices;

    private final Playlist playlistMock = new Playlist(1l, "playlist1", 1l, new ArrayList<Song>() {
        {
            add(new Song(1l, "song1", "genre1", "albumName1", new ArrayList<String>() {
                {
                    add("artist1");
                }
            }));
            add(new Song(2l, "song2", "genre2", "albumName2", new ArrayList<String>() {
                {
                    add("artist2");
                }
            }));
            add(new Song(3l, "song3", "genre3", "albumName3", new ArrayList<String>() {
                {
                    add("artist3");
                }
            }));
        }
    });

    @DisplayName("Must Play First Song In Current Playlist")
    @Test
    public void playPlaylistTest() {
        when(playlistServicesMock.getPlaylist(anyLong(), anyLong())).thenReturn(playlistMock);
        Song expectedSong = new Song(1l, "song1", "genre1", "albumName1", new ArrayList<String>() {
            {
                add("artist1");
            }
        });
        Song actualSong = playerServices.playPlaylist(1l, 1l);
        assertEquals(expectedSong, actualSong);
    }

    @DisplayName("Must Play Given Song If Present in Current Playlist")
    @Test
    public void playSongTest() {
        when(playlistServicesMock.getPlaylist(anyLong(), anyLong())).thenReturn(playlistMock);
        playerServices.playPlaylist(1l, 1l);
        Song expectedSong = new Song(3l, "song3", "genre3", "albumName3", new ArrayList<String>() {
            {
                add("artist3");
            }
        });
        Song actualSong = playerServices.playSong(1l,3l);
        assertEquals(expectedSong, actualSong);
    }

    @DisplayName("Must Play Next Song")
    @Test
    public void playNextSongTest() {
        when(playlistServicesMock.getPlaylist(anyLong(), anyLong())).thenReturn(playlistMock);
        playerServices.playPlaylist(1l, 1l);
        Song expectedSong = new Song(2l, "song2", "genre2", "albumName2", new ArrayList<String>() {
            {
                add("artist2");
            }
        });
        Song actualSong = playerServices.playNextSong(1l);
        assertEquals(expectedSong, actualSong);
    }

    @DisplayName("Must Play Previous Song")
    @Test
    public void playPreviousSongTest() {
        when(playlistServicesMock.getPlaylist(anyLong(), anyLong())).thenReturn(playlistMock);
        playerServices.playPlaylist(1l, 1l);
        Song expectedSong = new Song(3l, "song3", "genre3", "albumName3", new ArrayList<String>() {
            {
                add("artist3");
            }
        });
        Song actualSong = playerServices.playPreviousSong(1l);
        assertEquals(expectedSong, actualSong);
    }

    @DisplayName("Must Throw Empty Playlist Exception if Current Playlist Is Empty")
    @Test
    public void emptyPlaylistTest() {
        when(playlistServicesMock.getPlaylist(anyLong(), anyLong())).thenReturn(new Playlist(1l, "name", 1l, null));
        assertThrows(EmptyPlaylistException.class, ()->{
            playerServices.playPlaylist(1l, 1l);
        });
    }

    @DisplayName("Must Throw Song Not Found Exception if Song is Not Part of Current Playlist")
    @Test
    public void songNotPresentTest() {
        when(playlistServicesMock.getPlaylist(anyLong(), anyLong())).thenReturn(playlistMock);
        playerServices.playPlaylist(1l, 1l);
        assertThrows(SongNotFoundException.class, ()->{
            playerServices.playSong(1l, 4l);
        });
    }
}
