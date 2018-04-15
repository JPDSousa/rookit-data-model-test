
package org.rookit.dm.test.generator.track;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.kekstudio.musictheory.Key;

import java.util.Random;

import org.rookit.api.dm.track.Track;
import org.rookit.api.dm.track.TrackTitle;
import org.rookit.api.dm.track.TypeTrack;
import org.rookit.api.dm.track.TypeVersion;
import org.rookit.api.dm.track.VersionTrack;
import org.rookit.test.generator.EnumGenerator;
import org.rookit.test.generator.Generator;

@SuppressWarnings("javadoc")
public final class TrackGeneratorModule extends AbstractModule {
    
    @Provides
    private Generator<TypeTrack> getTypeTrackGenerator(final Random random) {
        return new EnumGenerator<>(random, TypeTrack.class);
    }
    
    @Provides
    private Generator<TypeVersion> getTypeVersionGenerator(final Random random) {
        return new EnumGenerator<>(random, TypeVersion.class);
    }
    
    @Override
    protected void configure() {
        bind(new TypeLiteral<Generator<Track>>() {
        }).to(OriginalTrackGenerator.class).in(Singleton.class);
        bind(new TypeLiteral<Generator<VersionTrack>>() {
        }).to(VersionTrackGenerator.class).in(Singleton.class);
        bind(new TypeLiteral<Generator<Key>>() {
        }).to(KeyGenerator.class);
        bind(new TypeLiteral<Generator<TrackTitle>>() {
        }).to(TrackTitleGenerator.class);
    }
}
