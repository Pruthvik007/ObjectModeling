package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.services.PlayerServices;

public class PlayNextSongCommand implements ICommand {

    private final PlayerServices playerServices;

    public PlayNextSongCommand(PlayerServices playerServices) {
        this.playerServices = playerServices;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Long userId = Long.parseLong(tokens.get(1));
            playerServices.playNextSong(userId);
        } catch (EmptyPlaylistException e) {
            System.out.print(e.getMessage());
        }
    }

}
