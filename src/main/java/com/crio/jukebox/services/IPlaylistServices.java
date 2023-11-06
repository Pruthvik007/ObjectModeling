package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;

public interface IPlaylistServices {
    public Playlist createPlaylist(Long userId, String playlistName, List<Long> songs)
            throws UserNotFoundException, SongNotFoundException;

    public Playlist getPlaylist(Long userId, Long playlistId) throws PlaylistNotFoundException;

    public void deletePlaylist(Long userId, Long playlistId) throws PlaylistNotFoundException;

    public Playlist addSongsToPlaylist(Long userId, Long playlistId, List<Long> newSongs)
            throws PlaylistNotFoundException, SongNotFoundException;

    public Playlist deleteSongsFromPlaylist(Long userId, Long playlistId, List<Long> songsToDelete)
            throws PlaylistNotFoundException, SongNotFoundException;
}
