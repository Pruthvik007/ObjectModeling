package com.crio.jukebox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.crio.jukebox.repositories.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SongServicesTest {
    @Mock
    private SongRepository songRepositoryMock;
    @InjectMocks
    private SongServices songServices;

    @Test
    public void loadSongsTest(){
        assertEquals(null, songServices.loadSongs("filePath"));
    }

    @Test
    public void getSongsTest(){
        assertEquals(null, songServices.getSongs(null));
    }
}
