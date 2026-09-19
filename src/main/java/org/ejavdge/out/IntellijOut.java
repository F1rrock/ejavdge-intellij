package org.ejavdge.out;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import org.ejavdge.error.InvariantViolation;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.workspace.out.Out;
import org.ejavdge.workspace.out.WithReport;
import org.ejavdge.workspace.out.WithoutAnsi;

public final class IntellijOut implements Out {
    private final Out origin;

    public IntellijOut(final ConsoleView v) {
        this.origin = new WithReport(
            new WithoutAnsi(
                new IntellijConsole(v)
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
