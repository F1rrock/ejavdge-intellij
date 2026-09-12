package org.ejavdge.log;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

public final class IntellijLoggerProvider implements SLF4JServiceProvider {

    private final ILoggerFactory factory =
        new IntellijLoggerFactory();

    private final IMarkerFactory markers =
        new BasicMarkerFactory();

    private final MDCAdapter mdc =
        new BasicMDCAdapter();

    @Override
    public ILoggerFactory getLoggerFactory() {
        return this.factory;
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return this.markers;
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return this.mdc;
    }

    @Override
    public String getRequestedApiVersion() {
        return "2.0.12";
    }

    @Override
    public void initialize() {
        // Already initialized by field construction.
    }
}
