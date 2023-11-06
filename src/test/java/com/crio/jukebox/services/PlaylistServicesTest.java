package com.crio.jukebox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("PlaylistServicesTest")
@ExtendWith(MockitoExtension.class)
public class PlaylistServicesTest {

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @Mock
    private SongRepository songRepositoryMock;
    @InjectMocks
    private PlaylistServices playlistServices;

    @DisplayName("Must Create Playlist")
    @Test
    public void createPlaylistTest() {
        Playlist expectedPlaylist = new Playlist(1l, "name", 1l, null);
        when(userRepositoryMock.getUser(anyLong())).thenReturn(new User(1l, "name"));
        when(playlistRepositoryMock.createOrUpdatePlaylist(any(Playlist.class))).thenReturn(expectedPlaylist);
        Playlist actualPlaylist = playlistServices.createPlaylist(1l, "name", null);
        assertEquals(expectedPlaylist, actualPlaylist);
    }

    @DisplayName("Must Return Playlist With Given Id")
    @Test
    public void getPlaylistTest() {
        Playlist expectedPlaylist = new Playlist(1l, "name", 1l, null);
        when(playlistRepositoryMock.getPlaylist(1l)).thenReturn(expectedPlaylist);
        Playlist actualPlaylist = playlistServices.getPlaylist(1l, 1l);
        assertEquals(expectedPlaylist, actualPlaylist);
    }

    @DisplayName("Must Delete Playlist With Given Id")
    @Test
    public void deletePlaylistTest() {        
    }

    @DisplayName("Must Add Given List of Songs To A Playlist")
    @Test
    public void addSongsToPlaylistTest() {
        Song songToAddMock = new Song(1l, "name", "genre", "albumName", new ArrayList<String>(){
            {
                add("artist");
            }
        });
        when(playlistRepositoryMock.getPlaylist(1l)).thenReturn(new Playlist(1l, "name", 1l, null));
        when(songRepositoryMock.getSong(1l)).thenReturn(songToAddMock);
        Playlist expectedPlaylist = new Playlist(1l, "name", 1l, new ArrayList<Song>(){
            {
                add(songToAddMock);
            }
        });
        when(playlistRepositoryMock.createOrUpdatePlaylist(any(Playlist.class))).thenReturn(expectedPlaylist);
        Playlist actualPlaylist = playlistServices.addSongsToPlaylist(1l, 1l, new ArrayList<Long>(){
            {
                add(1l);
            }
        });
        assertEquals(expectedPlaylist, actualPlaylist);
    }

    @DisplayName("Must Delete Given List of Songs From A Playlist")
    @Test
    public void deleteSongsFromPlaylistTest() {
        Song songToDeleteMock = new Song(1l, "name", "genre", "albumName", new ArrayList<String>(){
            {
                add("artist");
            }
        });
        when(playlistRepositoryMock.getPlaylist(1l)).thenReturn(new Playlist(1l, "name", 1l, new ArrayList<Song>(){
            {
                add(songToDeleteMock);
            }
        }));
        when(songRepositoryMock.getSong(1l)).thenReturn(songToDeleteMock);
        Playlist expectedPlaylist = new Playlist(1l, "name", 1l, new ArrayList<Song>());
        when(playlistRepositoryMock.createOrUpdatePlaylist(any(Playlist.class))).thenReturn(expectedPlaylist);
        Playlist actualPlaylist = playlistServices.deleteSongsFromPlaylist(1l, 1l, new ArrayList<Long>(){
            {
                add(1l);
            }
        });
        assertEquals(expectedPlaylist, actualPlaylist);
    }

    @DisplayName("Must Throw Playlist Not Found Exception")
    @Test
    public void getInvalidPlaylistTest() {
        when(playlistRepositoryMock.getPlaylist(anyLong())).thenReturn(null);
        assertThrows(PlaylistNotFoundException.class, ()->{
            playlistServices.getPlaylist(1l, 1l);
        });
    }

    @DisplayName("Must Throw Song Not Found Exception")
    @Test
    public void creatingPlaylistWithInvalidSongs() {
        when(userRepositoryMock.getUser(1l)).thenReturn(new User(1l, "name"));
        when(songRepositoryMock.getSong(1l)).thenReturn(null);
        assertThrows(SongNotFoundException.class, ()->{
            playlistServices.createPlaylist(1l, "playlistName", new ArrayList<Long>(){
                {
                    add(1l);
                }
            });
        });
    }

    @DisplayName("Must Throw Playlist Not Found Exception")
    @Test
    public void deleteInvalidPlaylistTest() {
        when(playlistRepositoryMock.getPlaylist(anyLong())).thenReturn(null);
        assertThrows(PlaylistNotFoundException.class, ()->{
            playlistServices.deletePlaylist(1l, 1l);
        });
    }

    @DisplayName("Must Throw Song Not Found Exception")
    @Test
    public void addInvalidSongsToPlaylistTest() {
        when(playlistRepositoryMock.getPlaylist(1l)).thenReturn(new Playlist(1l, "name", 1l, null));
        when(songRepositoryMock.getSong(1l)).thenReturn(null);
        assertThrows(SongNotFoundException.class, ()->{
            playlistServices.addSongsToPlaylist(1l, 1l, new ArrayList<Long>(){
                {
                    add(1l);
                }
            });
        });
    }

    @DisplayName("Must Throw Song Not Found Exception")
    @Test
    public void deleteInvalidSongsToPlaylistTest() {
        when(playlistRepositoryMock.getPlaylist(1l)).thenReturn(new Playlist(1l, "name", 1l, null));
        when(songRepositoryMock.getSong(1l)).thenReturn(null);
        assertThrows(SongNotFoundException.class, ()->{
            playlistServices.deleteSongsFromPlaylist(1l, 1l, new ArrayList<Long>(){
                {
                    add(1l);
                }
            });
        });
    }

    @DisplayName("Must Throw User Not Found Exception")
    @Test
    public void creatingPlaylistWithInvalidUserTest() {
        when(userRepositoryMock.getUser(anyLong())).thenReturn(null);
        assertThrows(UserNotFoundException.class, ()->{
            playlistServices.createPlaylist(1l, "playlistName", new ArrayList<Long>(){
                {
                    add(1l);
                }
            });
        });
    }

}
