
package org.rookit.dm.test.generator.artist;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

import java.util.Random;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.artist.GroupArtist;
import org.rookit.api.dm.artist.Musician;
import org.rookit.api.dm.artist.TypeGender;
import org.rookit.api.dm.artist.TypeGroup;
import org.rookit.test.generator.EnumGenerator;
import org.rookit.test.generator.Generator;

@SuppressWarnings("javadoc")
public final class ArtistGeneratorModule extends AbstractModule {
    @Provides
    private Generator<TypeGender> getTypeGenderGenerator(final Random random) {
        return new EnumGenerator<>(random, TypeGender.class);
    }

    @Provides
    private Generator<TypeGroup> getTypeGroupGenerator(final Random random) {
        return new EnumGenerator<>(random, TypeGroup.class);
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<Generator<Artist>>() {
        }).to(ArtistGenerator.class);
        bind(new TypeLiteral<Generator<GroupArtist>>() {
        }).to(GroupArtistGenerator.class).in(Singleton.class);
        bind(new TypeLiteral<Generator<Musician>>() {
        }).to(MusicianGenerator.class).in(Singleton.class);
    }
}
