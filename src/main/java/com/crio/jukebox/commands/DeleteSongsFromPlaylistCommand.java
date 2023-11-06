package com.crio.jukebox.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.PlaylistServices;

public class DeleteSongsFromPlaylistCommand implements ICommand {

    private final PlaylistServices playlistServices;

    public DeleteSongsFromPlaylistCommand(PlaylistServices playlistServices) {
        this.playlistServices = playlistServices;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Long userId = Long.parseLong(tokens.get(2));
            Long playlistId = Long.parseLong(tokens.get(3));
            List<Long> songIds = Arrays.asList(tokens.get(4).split(" ")).stream()
                    .map(songId -> Long.parseLong(songId)).collect(Collectors.toList());
            Playlist updatedPlaylist = playlistServices.deleteSongsFromPlaylist(userId, playlistId, songIds);
            System.out.printf("Playlist ID - %d\n",updatedPlaylist.getId());
            System.out.printf("Playlist Name - %s\n",updatedPlaylist.getName());
            System.out.printf("Song IDs - %s\n",updatedPlaylist.getSongIds());
        } catch (PlaylistNotFoundException | SongNotFoundException e) {
            System.out.print(e.getMessage());
        }
    }
}
