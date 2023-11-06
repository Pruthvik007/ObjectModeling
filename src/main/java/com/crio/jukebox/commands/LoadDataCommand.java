package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.SongServices;

public class LoadDataCommand implements ICommand {

    private final SongServices songServices;

    public LoadDataCommand(SongServices songServices) {
        this.songServices = songServices;
    }

    @Override
    public void execute(List<String> tokens) {
        String filePath = tokens.get(1);
        songServices.loadSongs(filePath);
        System.out.println("Songs Loaded successfully");
    }
}
