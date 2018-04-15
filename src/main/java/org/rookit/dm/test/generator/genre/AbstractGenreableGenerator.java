
package org.rookit.dm.test.generator.genre;

import static org.rookit.test.utils.GeneratorUtils.randomlyConsumeCollection;

import com.google.common.base.MoreObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

import javax.annotation.Generated;

import org.bson.types.ObjectId;
import org.rookit.api.dm.genre.Genre;
import org.rookit.api.dm.genre.Genreable;
import org.rookit.dm.test.generator.play.able.AbstractPlayableGenerator;
import org.rookit.test.generator.Generator;

@SuppressWarnings("javadoc")
public abstract class AbstractGenreableGenerator<T extends Genreable> extends AbstractPlayableGenerator<T> {

    private final Generator<Genre> genreGenerator;

    protected AbstractGenreableGenerator(final Generator<ObjectId> idGenerator,
            final Generator<Duration> durationGenerator,
            final Generator<LocalDate> pastGenerator,
            final Generator<Genre> genreGenerator,
            final Generator<Long> longGenerator) {
        super(idGenerator, durationGenerator, pastGenerator, longGenerator);
        this.genreGenerator = genreGenerator;
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public boolean equals(final Object object) {
        if (object instanceof AbstractGenreableGenerator) {
            if (!super.equals(object)) {
                return false;
            }
            final AbstractGenreableGenerator<?> that = (AbstractGenreableGenerator<?>) object;
            return Objects.equals(this.genreGenerator, that.genreGenerator);
        }
        return false;
    }

    @SuppressWarnings("boxing")
    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.genreGenerator);
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("genreGenerator", this.genreGenerator)
                .toString();
    }

    protected abstract void fillGenreable(T genreable);

    @Override
    protected final void fillPlayable(final T genreable) {
        randomlyConsumeCollection(genreable::setGenres, this.genreGenerator);
        fillGenreable(genreable);
    }

    protected final Generator<Genre> getGenreGenerator() {
        return this.genreGenerator;
    }

}
