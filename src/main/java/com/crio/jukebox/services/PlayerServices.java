package com.crio.jukebox.services;

import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class PlayerServices implements IPlayerServices {
    private Playlist selectedPlaylist;
    private Integer currentSongIndex;

    private PlaylistServices playlistServices;

    public PlayerServices(PlaylistServices playlistServices) {
        this.playlistServices = playlistServices;
    }

    @Override
    public Song playPlaylist(Long userId, Long playlistId) {
        setPlaylist(playlistServices.getPlaylist(userId, playlistId));
        validatePlaylist();
        setCurrentSongIndex(0);
        return playSong();
    }

    private void validatePlaylist(){
        if (selectedPlaylist==null || selectedPlaylist.getSongs() == null || selectedPlaylist.getSongs().size() == 0) {
            throw new EmptyPlaylistException("Playlist is empty.\n");
        }
    }

    private Song playSong() {
        Song currentSongBeingPlayed = selectedPlaylist.getSongs().get(currentSongIndex);
        System.out.println("Current Song Playing");
        System.out.printf("Song - %s\n", currentSongBeingPlayed.getName());
        System.out.printf("Album - %s\n", currentSongBeingPlayed.getAlbumName());
        StringBuilder artistsOfCurrentSong = new StringBuilder("");
        for(int i=0;i<currentSongBeingPlayed.getArtists().size();i++){
            artistsOfCurrentSong.append(currentSongBeingPlayed.getArtists().get(i));
            if(i<currentSongBeingPlayed.getArtists().size()-1){
                artistsOfCurrentSong.append(",");
            }
        }
        System.out.printf("Artists - %s\n",artistsOfCurrentSong.toString());
        return currentSongBeingPlayed;
    }

    private Optional<Song> getSelectedSong(Long songId) {
        return selectedPlaylist.getSongs().stream().filter(song -> song.getId() == songId)
                .findAny();
    }

    @Override
    public Song playSong(Long userId, Long songId) {
        validatePlaylist();
        Optional<Song> selectedSong = getSelectedSong(songId);
        if (selectedSong.isEmpty()) {
            throw new SongNotFoundException("Given song id is not a part of the active playlist\n");
        } else {
            setCurrentSongIndex(selectedPlaylist.getSongs().indexOf(selectedSong.get()));
            return playSong();
        }
    }

    @Override
    public Song playNextSong(Long userId) {
        validatePlaylist();
        if (currentSongIndex == selectedPlaylist.getSongs().size() - 1) {
            currentSongIndex = 0;
        } else {
            currentSongIndex++;
        }
        return playSong();
    }

    @Override
    public Song playPreviousSong(Long userId) {
        validatePlaylist();
        if (currentSongIndex == 0) {
            currentSongIndex = selectedPlaylist.getSongs().size() - 1;
        } else {
            currentSongIndex--;
        }
        return playSong();
    }

    private void setPlaylist(Playlist playlist) {
        selectedPlaylist = playlist;
    }

    private void setCurrentSongIndex(Integer currentSongIndex) {
        this.currentSongIndex = currentSongIndex;
    }
}
