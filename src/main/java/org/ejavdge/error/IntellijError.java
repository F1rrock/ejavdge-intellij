package org.ejavdge.error;

import org.jetbrains.annotations.NotNull;

public class IntellijError extends RuntimeException {
    public IntellijError(final @NotNull String message) {
        super(message);
    }

    public IntellijError(final @NotNull String message, final @NotNull Throwable cause) {
        super(message, cause);
    }
}
