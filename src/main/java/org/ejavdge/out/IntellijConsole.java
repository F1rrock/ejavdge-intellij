package org.ejavdge.out;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import org.ejavdge.error.InvariantViolation;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.workspace.out.Out;

public final class IntellijConsole implements Out {
    private final ConsoleView console;
    private final ConsoleViewContentType type;

    public IntellijConsole(final ConsoleView v) {
        this(v, ConsoleViewContentType.NORMAL_OUTPUT);
    }

    public IntellijConsole(final ConsoleView v, final ConsoleViewContentType t) {
        this.console = v;
        this.type = t;
    }

    @Override
    public void write(final Text text) throws InvariantViolation {
        this.console.clear();
        this.console.print(
            text.content(),
            this.type
        );
    }
}
