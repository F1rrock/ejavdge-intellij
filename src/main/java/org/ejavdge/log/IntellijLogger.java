package org.ejavdge.log;

import com.intellij.openapi.diagnostic.Logger;
import org.slf4j.event.Level;
import org.slf4j.helpers.AbstractLogger;
import org.slf4j.Marker;

public final class IntellijLogger extends AbstractLogger {
    private final Logger origin;

    public IntellijLogger(final String name) {
        this.name = name;
        this.origin = Logger.getInstance(name);
    }

    @Override
    public boolean isTraceEnabled() {
        return this.origin.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return this.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return this.origin.isDebugEnabled();
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return this.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return this.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return this.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override
    protected void handleNormalizedLoggingCall(
        final Level level,
        final Marker marker,
        final String message,
        final Object[] arguments,
        final Throwable throwable
    ) {
        final var text = this.substitute(message, arguments);
        switch (level) {
            case TRACE, DEBUG:
                this.origin.debug(text);
                break;
            case INFO:
                this.origin.info(text);
                break;
            case WARN:
                this.origin.warn(text, throwable);
                break;
            case ERROR:
                this.origin.error(text, throwable);
                break;
        }
    }

    private String substitute(
        final String message,
        final Object[] arguments
    ) {
        if (arguments == null || arguments.length == 0) {
            return message;
        }
        return org.slf4j.helpers.MessageFormatter
            .arrayFormat(message, arguments)
            .getMessage();
    }

    @Override
    protected String getFullyQualifiedCallerName() {
        return IntellijLogger.class.getName();
    }
}
