package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.services.PlaylistServices;

public class DeletePlaylistCommand implements ICommand {

    private final PlaylistServices playlistServices;

    public DeletePlaylistCommand(PlaylistServices playlistServices) {
        this.playlistServices = playlistServices;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Long userId = Long.parseLong(tokens.get(1));
            Long playlistId = Long.parseLong(tokens.get(2));
            playlistServices.deletePlaylist(userId, playlistId);
            System.out.println("Delete Successful");
        } catch (PlaylistNotFoundException e) {
            System.out.print(e.getMessage());
        }
    }
}
