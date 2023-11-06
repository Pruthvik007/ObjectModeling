package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.services.PlayerServices;

public class PlayPlaylistCommand implements ICommand {

    private final PlayerServices playerServices;

    public PlayPlaylistCommand(PlayerServices playerServices) {
        this.playerServices = playerServices;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Long userId = Long.parseLong(tokens.get(1));
            Long playlistId = Long.parseLong(tokens.get(2));
            playerServices.playPlaylist(userId, playlistId);
        } catch (EmptyPlaylistException e) {
            System.out.print(e.getMessage());
        }
    }
}
