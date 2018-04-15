package org.rookit.dm.test.mixin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.rookit.api.dm.artist.GroupArtist;
import org.rookit.api.dm.artist.TypeArtist;
import org.rookit.api.dm.artist.factory.ArtistFactory;
import org.rookit.api.dm.artist.key.ArtistKey;

@SuppressWarnings("javadoc")
public interface ArtistMixin {
    
    default ArtistKey createGroupArtistKey(final String groupName) {
        final ArtistKey mockedKey = mock(ArtistKey.class);
        when(mockedKey.getArtistType()).thenReturn(TypeArtist.GROUP);
        when(mockedKey.getName()).thenReturn(groupName);
        
        return mockedKey;
    }
    
    default GroupArtist createGroupArtist(final String groupName) {
        final ArtistKey artistKey = createGroupArtistKey(groupName);
        
        return getArtistFactory()
                .createGroupArtist(artistKey);
    }
    
    ArtistFactory getArtistFactory();

}
