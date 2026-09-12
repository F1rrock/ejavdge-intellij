package org.ejavdge.log;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public final class IntellijLoggerFactory implements ILoggerFactory {
    private final ConcurrentMap<String, Logger> loggers =
        new ConcurrentHashMap<>();

    @Override
    public Logger getLogger(final String name) {
        return this.loggers.computeIfAbsent(
            name,
            IntellijLogger::new
        );
    }
}
