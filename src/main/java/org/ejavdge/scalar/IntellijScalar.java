package org.ejavdge.scalar;

import org.ejavdge.error.IntellijError;

public interface IntellijScalar<T> {
    T value() throws IntellijError;
}
