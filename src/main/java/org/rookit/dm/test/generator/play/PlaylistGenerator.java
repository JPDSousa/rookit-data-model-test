
package org.rookit.dm.test.generator.play;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

import javax.annotation.Generated;

import org.bson.types.ObjectId;
import org.rookit.api.dm.play.Playlist;
import org.rookit.api.dm.play.factory.PlaylistFactory;
import org.rookit.dm.test.generator.play.able.AbstractPlayableGenerator;
import org.rookit.test.generator.Generator;

class PlaylistGenerator extends AbstractPlayableGenerator<Playlist> {

    private final PlaylistFactory playlistFactory;

    private final Generator<String> stringGenerator;
    private final Generator<byte[]> byteArrayGenerator;

    @Inject
    private PlaylistGenerator(final PlaylistFactory playlistFactory,
            final Generator<ObjectId> idGenerator,
            final Generator<Duration> durationGenerator,
            final Generator<LocalDate> pastGenerator,
            final Generator<Long> longGenerator,
            final Generator<String> stringGenerator,
            final Generator<byte[]> byteArrayGenerator) {
        super(idGenerator, durationGenerator, pastGenerator, longGenerator);
        this.playlistFactory = playlistFactory;
        this.stringGenerator = stringGenerator;
        this.byteArrayGenerator = byteArrayGenerator;
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public boolean equals(final Object object) {
        if (object instanceof PlaylistGenerator) {
            if (!super.equals(object)) {
                return false;
            }
            final PlaylistGenerator that = (PlaylistGenerator) object;
            return Objects.equals(this.playlistFactory, that.playlistFactory)
                    && Objects.equals(this.stringGenerator, that.stringGenerator)
                    && Objects.equals(this.byteArrayGenerator, that.byteArrayGenerator);
        }
        return false;
    }

    @SuppressWarnings("boxing")
    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.playlistFactory, this.stringGenerator, this.byteArrayGenerator);
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("playlistFactory", this.playlistFactory)
                .add("stringGenerator", this.stringGenerator)
                .add("byteArrayGenerator", this.byteArrayGenerator)
                .toString();
    }

    @Override
    protected Playlist constructRandom() {
        return this.playlistFactory
                .createStaticPlaylist(this.stringGenerator.createRandom());
    }

    @Override
    protected void fillPlayable(final Playlist playlist) {
        playlist.setImage(this.byteArrayGenerator.createRandom());
    }

}
