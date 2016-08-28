package com.ksubaka.album.service;

/**
 * Created by zhuanghua on 16/8/28.
 */

import com.ksubaka.album.dto.AlbumDto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AlbumRetrieveTest {

    IAlbumRetrieve albumRetrieve = new AlbumRetrieveOmdbImpl();

    @Test
    public final void testOmdbRetrieve_1() {
        List<AlbumDto> outcome = albumRetrieve.retrieveAlbum("Indiana Jones");

        for (AlbumDto dto : outcome) {
            System.out.println(dto.getTitle() + " | " + dto.getType()
                    + " | " + dto.getImdbId() + " | " + dto.getYear());
        }
        assertNotNull(outcome);
        assertTrue(outcome.size() > 0);
    }

    @Test
    public final void testOmdbRetrieve_2() {
        List<AlbumDto> outcome = albumRetrieve.retrieveAlbum("IndianaXXXJones");

        assertNotNull(outcome);
        assertEquals(0, outcome.size());

    }

}
