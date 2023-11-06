package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.PlaylistServices;

public class CreatePlaylistCommand implements ICommand {

    private final PlaylistServices playlistServices;

    public CreatePlaylistCommand(PlaylistServices playlistServices) {
        this.playlistServices = playlistServices;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Long userId = Long.parseLong(tokens.get(1));
            String playlistName = tokens.get(2);
            List<Long> songIds = extractSongIds(tokens);
            Playlist createdPlaylist =
                    playlistServices.createPlaylist(userId, playlistName, songIds);
            System.out.printf("Playlist ID - %d\n",createdPlaylist.getId());
        } catch (UserNotFoundException | SongNotFoundException e) {
            System.out.print(e.getMessage());
        }
    }

    private List<Long> extractSongIds(List<String> tokens) {
        List<Long> songIds = new ArrayList<>();
        int index = 0;
        for (String token : tokens) {
            if (index > 2) {
                songIds.add(Long.parseLong(token));
            }
            index++;
        }
        return songIds;
    }
}
