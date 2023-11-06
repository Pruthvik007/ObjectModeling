package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.Map;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepository {
    private Map<Long, Playlist> playlists;

    public PlaylistRepository() {
        this.playlists = new HashMap<>();
    }

    public PlaylistRepository(Map<Long, Playlist> playlists) {
        this.playlists = playlists;
    }

    private Long generatePlaylistId() {
        return playlists.size() + 1l;
    }

    public Playlist createOrUpdatePlaylist(Playlist playlist) {
        if (playlist.getId() == null) {
            playlist.setId(generatePlaylistId());
        }
        playlists.put(playlist.getId(), playlist);
        return playlist;
    }

    public Playlist getPlaylist(Long playlistId) {
        return this.playlists.getOrDefault(playlistId, null);
    }

    public void deletePlaylist(Long playlistId) {
        this.playlists.remove(playlistId);
    }

}
