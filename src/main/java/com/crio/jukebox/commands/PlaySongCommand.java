package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.PlayerServices;

public class PlaySongCommand implements ICommand {

    private final String PLAY_NEXT_COMMAND = "NEXT";
    private final String PLAY_PREVIOUS_COMMAND = "BACK";

    private final PlayerServices playerServices;
    private final PlayNextSongCommand playNextSongCommand;
    private final PlayPreviousSongCommand playPreviousSongCommand;

    public PlaySongCommand(PlayerServices playerServices, PlayNextSongCommand playNextSongCommand,
            PlayPreviousSongCommand playPreviousSongCommand) {
        this.playerServices = playerServices;
        this.playNextSongCommand = playNextSongCommand;
        this.playPreviousSongCommand = playPreviousSongCommand;
    }

    @Override
    public void execute(List<String> tokens) {
        String command = tokens.get(2);
        if (PLAY_NEXT_COMMAND.equals(command)) {
            playNextSongCommand.execute(tokens);
        } else if (PLAY_PREVIOUS_COMMAND.equals(command)) {
            playPreviousSongCommand.execute(tokens);
        } else {
            try {
                Long userId = Long.parseLong(tokens.get(1)), songId = Long.parseLong(tokens.get(2));
                playerServices.playSong(userId, songId);
            } catch (SongNotFoundException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
