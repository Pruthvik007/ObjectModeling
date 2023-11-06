package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.PlaylistServices;

public class AddSongsToPlaylistCommand implements ICommand {

    private final PlaylistServices playlistServices;

    public AddSongsToPlaylistCommand(PlaylistServices playlistServices) {
        this.playlistServices = playlistServices;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Long userId = Long.parseLong(tokens.get(2));
            Long playlistId = Long.parseLong(tokens.get(3));
            List<Long> songIds = extractSongIds(tokens);
            Playlist updatedPlaylist =
                    playlistServices.addSongsToPlaylist(userId, playlistId, songIds);
            System.out.printf("Playlist ID - %d\n", updatedPlaylist.getId());
            System.out.printf("Playlist Name - %s\n", updatedPlaylist.getName());
            System.out.printf("Song IDs - %s\n", updatedPlaylist.getSongIds());
        } catch (PlaylistNotFoundException | SongNotFoundException e) {
            System.out.print(e.getMessage());
        }
    }

    private List<Long> extractSongIds(List<String> tokens) {
        List<Long> songIds = new ArrayList<>();
        int index = 0;
        for (String token : tokens) {
            if (index > 3) {
                songIds.add(Long.parseLong(token));
            }
            index++;
        }
        return songIds;
    }
}
