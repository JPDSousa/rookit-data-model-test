package org.rookit.dm.test.mixin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.rookit.api.dm.album.Album;
import org.rookit.api.dm.album.TypeAlbum;
import org.rookit.api.dm.album.TypeRelease;
import org.rookit.api.dm.album.factory.AlbumFactory;
import org.rookit.api.dm.album.key.AlbumKey;

@SuppressWarnings("javadoc")
public interface AlbumMixin {
    
    default AlbumKey createSingleArtistAlbumKey(final String title, final TypeRelease release) {
        final AlbumKey mockedKey = mock(AlbumKey.class);
        when(mockedKey.getAlbumType()).thenReturn(TypeAlbum.ARTIST);
        when(mockedKey.getTitle()).thenReturn(title);
        when(mockedKey.getType()).thenReturn(release);
        
        return mockedKey;
    }
    
    default AlbumKey createVariousArtistsAlbumKey(final String title, final TypeRelease release) {
        final AlbumKey mockedKey = mock(AlbumKey.class);
        when(mockedKey.getAlbumType()).thenReturn(TypeAlbum.VA);
        when(mockedKey.getTitle()).thenReturn(title);
        when(mockedKey.getType()).thenReturn(release);
        
        return mockedKey;
    }
    
    default Album createSingleArtistAlbum(final String title, final TypeRelease release) {
        final AlbumKey albumKey = createSingleArtistAlbumKey(title, release);
        
        return getAlbumFactory()
                .createSingleArtistAlbum(albumKey);
    }
    
    default Album createVariousArtistsAlbum(final String title, final TypeRelease release) {
        final AlbumKey albumKey = createVariousArtistsAlbumKey(title, release);
        
        return getAlbumFactory()
                .createSingleArtistAlbum(albumKey);
    }
    
    AlbumFactory getAlbumFactory();

}
