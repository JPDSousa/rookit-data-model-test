package org.rookit.dm.test.mixin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.track.Track;
import org.rookit.api.dm.track.TypeTrack;
import org.rookit.api.dm.track.TypeVersion;
import org.rookit.api.dm.track.VersionTrack;
import org.rookit.api.dm.track.factory.TrackFactory;
import org.rookit.api.dm.track.key.TrackKey;

@SuppressWarnings("javadoc")
public interface TrackMixin {
    
    default TrackKey createOriginalTrackKey(final String title, final Collection<Artist> mainArtists) {
        final TrackKey mockedKey = mock(TrackKey.class);
        when(mockedKey.getTrackType()).thenReturn(TypeTrack.ORIGINAL);
        when(mockedKey.getMainArtists()).thenReturn(mainArtists);
        when(mockedKey.getTitle()).thenReturn(title);
        
        return mockedKey;
    }
    
    default TrackKey createVersionTrackKey(final Track original,
            final TypeVersion versionType,
            final Collection<Artist> versionArtists,
            final String versionToken) {
        final TrackKey mockedKey = mock(TrackKey.class);
        when(mockedKey.getOriginal()).thenReturn(original);
        when(mockedKey.getTrackType()).thenReturn(TypeTrack.VERSION);
        when(mockedKey.getVersionType()).thenReturn(versionType);
        when(mockedKey.getVersionArtists()).thenReturn(versionArtists);
        when(mockedKey.getVersionToken()).thenReturn(versionToken);
        
        return mockedKey;
    }
    
    default TrackKey createVersionTrackKey(final Track original,
            final TypeVersion versionType,
            final Collection<Artist> versionArtists) {
        final TrackKey mockedKey = mock(TrackKey.class);
        when(mockedKey.getOriginal()).thenReturn(original);
        when(mockedKey.getTrackType()).thenReturn(TypeTrack.VERSION);
        when(mockedKey.getVersionType()).thenReturn(versionType);
        when(mockedKey.getVersionArtists()).thenReturn(versionArtists);
        
        return mockedKey;
    }
    
    default Track createOriginalTrack(final String title, final Collection<Artist> mainArtists) {
        final TrackKey trackKey = createOriginalTrackKey(title, mainArtists);
        return getTrackFactory()
                .createOriginalTrack(trackKey);
    }
    
    default VersionTrack createVersionTrack(final Track original,
            final TypeVersion versionType,
            final Collection<Artist> versionArtists,
            final String versionToken) {
        final TrackKey trackKey = createVersionTrackKey(original, versionType, versionArtists, versionToken);
        return getTrackFactory()
                .createVersionTrack(trackKey);
    }
    
    default VersionTrack createVersionTrack(final Track original,
            final TypeVersion versionType,
            final Collection<Artist> versionArtists) {
        final TrackKey trackKey = createVersionTrackKey(original, versionType, versionArtists);
        return getTrackFactory()
                .createVersionTrack(trackKey);
    }
    
    TrackFactory getTrackFactory();

}
