
package org.rookit.dm.test.generator.artist;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.rookit.test.utils.GeneratorUtils.randomlyConsumeCollection;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

import javax.annotation.Generated;

import org.bson.types.ObjectId;
import org.rookit.api.dm.artist.GroupArtist;
import org.rookit.api.dm.artist.Musician;
import org.rookit.api.dm.artist.TypeGroup;
import org.rookit.api.dm.artist.factory.ArtistFactory;
import org.rookit.api.dm.artist.key.ArtistKey;
import org.rookit.api.dm.genre.Genre;
import org.rookit.test.generator.Generator;

class GroupArtistGenerator extends AbstractArtistGenerator<GroupArtist> {

    private static final String PREFIX = "GroupArtist";

    private final ArtistFactory artistFactory;
    private final Generator<TypeGroup> groupTypeGenerator;
    private final Generator<Musician> musicianGenerator;
    private final Generator<String> stringGenerator;

    @Inject
    private GroupArtistGenerator(final ArtistFactory artistFactory,
            final Generator<Musician> musicianGenerator,
            final Generator<TypeGroup> groupTypeGenerator,
            final Generator<ObjectId> idGenerator,
            final Generator<Duration> durationGenerator,
            final Generator<LocalDate> pastGenerator,
            final Generator<Genre> genreGenerator,
            final Generator<Long> longGenerator,
            final Generator<String> stringGenerator,
            final Generator<byte[]> byteArrayGenerator) {
        super(idGenerator, durationGenerator, pastGenerator, genreGenerator,
                longGenerator, stringGenerator, byteArrayGenerator);
        this.artistFactory = artistFactory;
        this.groupTypeGenerator = groupTypeGenerator;
        this.musicianGenerator = musicianGenerator;
        this.stringGenerator = stringGenerator;
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public boolean equals(final Object object) {
        if (object instanceof GroupArtistGenerator) {
            if (!super.equals(object)) {
                return false;
            }
            final GroupArtistGenerator that = (GroupArtistGenerator) object;
            return Objects.equals(this.artistFactory, that.artistFactory)
                    && Objects.equals(this.groupTypeGenerator, that.groupTypeGenerator)
                    && Objects.equals(this.musicianGenerator, that.musicianGenerator)
                    && Objects.equals(this.stringGenerator, that.stringGenerator);
        }
        return false;
    }

    @SuppressWarnings("boxing")
    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.artistFactory, this.groupTypeGenerator, this.musicianGenerator,
                this.stringGenerator);
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("PREFIX", PREFIX)
                .add("artistFactory", this.artistFactory)
                .add("groupTypeGenerator", this.groupTypeGenerator)
                .add("musicianGenerator", this.musicianGenerator)
                .add("stringGenerator", this.stringGenerator)
                .toString();
    }

    @Override
    protected GroupArtist constructRandom() {
        final String name = PREFIX + this.stringGenerator.createRandom();
        final String isni = this.stringGenerator.createRandom();

        final ArtistKey mockedKey = mock(ArtistKey.class);
        when(mockedKey.getName()).thenReturn(name);
        when(mockedKey.getISNI()).thenReturn(isni);
        when(mockedKey.getGroupType()).thenReturn(this.groupTypeGenerator.createRandom());

        return this.artistFactory.createGroupArtist(mockedKey);
    }

    @Override
    protected void fillArtist(final GroupArtist artist) {
        randomlyConsumeCollection(artist::setMembers, this.musicianGenerator);
    }

}
