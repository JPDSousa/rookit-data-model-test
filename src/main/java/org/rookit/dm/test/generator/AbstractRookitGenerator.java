
package org.rookit.dm.test.generator;

import static org.rookit.test.utils.GeneratorUtils.randomlyConsume;

import com.google.common.base.MoreObjects;

import java.util.Objects;

import javax.annotation.Generated;

import org.bson.types.ObjectId;
import org.rookit.api.dm.RookitModel;
import org.rookit.test.generator.AbstractGenerator;
import org.rookit.test.generator.Generator;

@SuppressWarnings("javadoc")
public abstract class AbstractRookitGenerator<T extends RookitModel> extends AbstractGenerator<T> {

    private final Generator<ObjectId> idGenerator;

    protected AbstractRookitGenerator(final Generator<ObjectId> idGenerator) {
        super();
        this.idGenerator = idGenerator;
    }

    @Override
    public final T createRandom() {
        final T item = constructRandom();
        randomlyConsume(item::setId, this.idGenerator);
        fillRookitModel(item);

        return item;
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public boolean equals(final Object object) {
        if (object instanceof AbstractRookitGenerator) {
            if (!super.equals(object)) {
                return false;
            }
            final AbstractRookitGenerator<?> that = (AbstractRookitGenerator<?>) object;
            return Objects.equals(this.idGenerator, that.idGenerator);
        }
        return false;
    }

    @SuppressWarnings("boxing")
    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.idGenerator);
    }

    @Override
    @Generated(value = "GuavaEclipsePlugin")
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("idGenerator", this.idGenerator)
                .toString();
    }

    protected abstract T constructRandom();

    protected abstract void fillRookitModel(final T item);

}
