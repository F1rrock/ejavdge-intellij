package org.ejavdge.out;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import org.ejavdge.error.InvariantViolation;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.workspace.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IntellijOut implements Out {
    private final Out origin;

    public IntellijOut(final ConsoleView v) {
        this(v, LoggerFactory.getLogger(IntellijOut.class));
    }

    public IntellijOut(final ConsoleView v, final Logger l) {
        this.origin = new WithReport(
            new WithStackLog(
                new WithoutAnsi(
                    new IntellijConsole(v)
                ),
                l
            ),
            new WithoutAnsi(
                new IntellijConsole(
                    v,
                    ConsoleViewContentType.ERROR_OUTPUT
                )
            )
        );
    }

    @Override
    public void write(final Text t) throws InvariantViolation {
        this.origin.write(t);
    }
}
