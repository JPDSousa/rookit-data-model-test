package org.rookit.dm.test.generator;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

import org.bson.types.ObjectId;
import org.rookit.test.generator.Generator;

@SuppressWarnings("javadoc")
public final class RookitGeneratorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<Generator<ObjectId>>() {
        }).to(ObjectIdGenerator.class).in(Singleton.class);
    }
}