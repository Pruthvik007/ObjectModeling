package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public interface IPlayerServices {

    public Song playPlaylist(Long userId, Long playlistId) throws EmptyPlaylistException;

    public Song playSong(Long userId, Long songId) throws SongNotFoundException;

    public Song playNextSong(Long userId) throws EmptyPlaylistException;

    public Song playPreviousSong(Long userId) throws EmptyPlaylistException;

}
