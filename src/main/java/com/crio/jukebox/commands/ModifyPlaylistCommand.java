package com.crio.jukebox.commands;

import java.util.List;

public class ModifyPlaylistCommand implements ICommand {
    private final String ADD_SONGS_COMMAND = "ADD-SONG";
    private final String DELETE_SONGS_COMMAND = "DELETE-SONG";
    private final AddSongsToPlaylistCommand addSongsToPlaylistCommand;
    private final DeleteSongsFromPlaylistCommand deleteSongsFromPlaylistCommand;

    public ModifyPlaylistCommand(AddSongsToPlaylistCommand addSongsToPlaylistCommand,
            DeleteSongsFromPlaylistCommand deleteSongsFromPlaylistCommand) {
        this.addSongsToPlaylistCommand = addSongsToPlaylistCommand;
        this.deleteSongsFromPlaylistCommand = deleteSongsFromPlaylistCommand;
    }

    @Override
    public void execute(List<String> tokens) {
        String modifyCommand = tokens.get(1);
        if (ADD_SONGS_COMMAND.equals(modifyCommand)) {
            addSongsToPlaylistCommand.execute(tokens);
        }
        if (DELETE_SONGS_COMMAND.equals(modifyCommand)) {
            deleteSongsFromPlaylistCommand.execute(tokens);
        }
    }

}
