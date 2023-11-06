package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;

public class PlaylistServices implements IPlaylistServices {

    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;
    private SongRepository songRepository;

    public PlaylistServices(PlaylistRepository playlistRepository, UserRepository userRepository,
            SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public Playlist createPlaylist(Long userId, String playlistName, List<Long> songIds) {
        validateUser(userId);
        List<Song> songs = getSongs(songIds);
        if (songIds != null && songIds.size() != songs.size()) {
            throw new SongNotFoundException(
                    "Some Requested Songs Not Available. Please try again.\n");
        }
        return playlistRepository.createOrUpdatePlaylist(new Playlist(playlistName, userId, songs));
    }

    @Override
    public Playlist getPlaylist(Long userId, Long playlistId) {
        Playlist playlist = playlistRepository.getPlaylist(playlistId);
        if (playlist == null || playlist.getUserId() != userId) {
            throw new PlaylistNotFoundException("Playlist Not Found\n");
        }
        return playlist;
    }

    @Override
    public void deletePlaylist(Long userId, Long playlistId) {
        getPlaylist(userId, playlistId);
        playlistRepository.deletePlaylist(playlistId);
    }

    @Override
    public Playlist addSongsToPlaylist(Long userId, Long playlistId, List<Long> idsOfSongsToAdd) {
        Playlist playlist = getPlaylist(userId, playlistId);
        List<Song> songsToAdd = getSongs(idsOfSongsToAdd);
        if (songsToAdd.size() != idsOfSongsToAdd.size()) {
            throw new SongNotFoundException(
                    "Some Requested Songs Not Available. Please try again.\n");
        }
        List<Song> existingSongsInPlaylist =
                playlist.getSongs() != null ? playlist.getSongs() : new ArrayList<>();
        Set<Song> filterSongsToAdd = new LinkedHashSet<>();
        filterSongsToAdd.addAll(existingSongsInPlaylist);
        filterSongsToAdd.addAll(songsToAdd);
        playlist.setSongs(filterSongsToAdd.stream().collect(Collectors.toList()));
        return playlistRepository.createOrUpdatePlaylist(playlist);
    }

    @Override
    public Playlist deleteSongsFromPlaylist(Long userId, Long playlistId,
            List<Long> idsOfSongsToDelete) {
        Playlist playlist = getPlaylist(userId, playlistId);
        List<Song> existingSongs = playlist.getSongs();
        List<Song> songsToDelete = getSongs(idsOfSongsToDelete);
        if (existingSongs == null || idsOfSongsToDelete.size() != songsToDelete.size()) {
            throw new SongNotFoundException(
                    "Some Requested Songs for Deletion are not present in the playlist. Please try again.\n");
        }
        existingSongs.removeAll(songsToDelete);
        playlist.setSongs(existingSongs);
        return playlistRepository.createOrUpdatePlaylist(playlist);
    }

    private List<Song> getSongs(List<Long> songIds) {
        if (songIds == null) {
            return null;
        }
        List<Song> songs = new ArrayList<>();
        for (Long songId : songIds) {
            Song song = songRepository.getSong(songId);
            if (song != null) {
                songs.add(song);
            }
        }
        return songs;
    }

    private void validateUser(Long userId) {
        if (getUser(userId) == null) {
            throw new UserNotFoundException("User Not Found\n");
        }
    }

    private User getUser(Long userId) {
        return userRepository.getUser(userId);
    }

}
