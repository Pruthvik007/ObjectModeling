package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.AddSongsToPlaylistCommand;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.DeleteSongsFromPlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayNextSongCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlayPreviousSongCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.PlayerServices;
import com.crio.jukebox.services.PlaylistServices;
import com.crio.jukebox.services.SongServices;
import com.crio.jukebox.services.UserServices;

public class ApplicationConfig {
    private final UserRepository userRepository = new UserRepository();
    private final SongRepository songRepository = new SongRepository();
    private final PlaylistRepository playlistRepository = new PlaylistRepository();

    private final UserServices userServices = new UserServices(userRepository);
    private final SongServices songServices = new SongServices(songRepository);
    private final PlaylistServices playlistServices = new PlaylistServices(playlistRepository,userRepository,songRepository);
    private final PlayerServices playerServices = new PlayerServices(playlistServices);

    private final AddSongsToPlaylistCommand addSongsToPlaylistCommand = new AddSongsToPlaylistCommand(playlistServices);
    private final DeleteSongsFromPlaylistCommand deleteSongsFromPlaylistCommand = new DeleteSongsFromPlaylistCommand(playlistServices);
    private final ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(addSongsToPlaylistCommand, deleteSongsFromPlaylistCommand);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistServices);
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userServices);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistServices);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songServices);
    private final PlayNextSongCommand playNextSongCommand = new PlayNextSongCommand(playerServices);
    private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(playerServices);
    private final PlayPreviousSongCommand playPreviousSongCommand = new PlayPreviousSongCommand(playerServices);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(playerServices,playNextSongCommand,playPreviousSongCommand);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA",loadDataCommand);
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyPlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST",playPlaylistCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
        return commandInvoker;
    }
}
