
package org.rookit.dm.test.generator;

import org.bson.types.ObjectId;
import org.rookit.test.generator.AbstractGenerator;

class ObjectIdGenerator extends AbstractGenerator<ObjectId> {

    @Override
    public ObjectId createRandom() {
        return new ObjectId();
    }
    
}
