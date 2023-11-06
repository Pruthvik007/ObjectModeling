package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.services.PlayerServices;

public class PlayPreviousSongCommand implements ICommand {

    private final PlayerServices playerServices;

    public PlayPreviousSongCommand(PlayerServices playerServices) {
        this.playerServices = playerServices;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Long userId = Long.parseLong(tokens.get(1));
            playerServices.playPreviousSong(userId);
        } catch (EmptyPlaylistException e) {
            System.out.print(e.getMessage());
        }
    }

}
