
package org.rookit.dm.test.generator.track;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;

import java.util.Objects;

import javax.annotation.Generated;

import org.rookit.api.dm.track.TrackTitle;
import org.rookit.test.generator.AbstractGenerator;
import org.rookit.test.generator.Generator;

class TrackTitleGenerator extends AbstractGenerator<TrackTitle> {

    private final Generator<String> stringGenerator;

    @Inject
    private TrackTitleGenerator(final Generator<String> stringGenerator) {
        super();
        this.stringGenerator = stringGenerator;
    }

    @Override
    public TrackTitle createRandom() {
        return new TrackTitle(this.stringGenerator.createRandom());
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public boolean equals(final Object object) {
        if (object instanceof TrackTitleGenerator) {
            if (!super.equals(object)) {
                return false;
            }
            final TrackTitleGenerator that = (TrackTitleGenerator) object;
            return Objects.equals(this.stringGenerator, that.stringGenerator);
        }
        return false;
    }

    @SuppressWarnings("boxing")
    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.stringGenerator);
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("stringGenerator", this.stringGenerator)
                .toString();
    }

}
