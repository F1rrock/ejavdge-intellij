package org.ejavdge.out;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.ejavdge.error.InvariantViolation;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.workspace.out.Out;

public final class IntellijErrorDialog implements Out {
    private final Project project;
    private final Text title;

    public IntellijErrorDialog(final Project p, final String t) {
        this(p, new Text.Of(t));
    }

    public IntellijErrorDialog(final Project p, final Text t) {
        this.project = p;
        this.title = t;
    }

    @Override
    public void write(final Text t) throws InvariantViolation {
        if (!this.project.isDisposed()) {
            Messages.showErrorDialog(
                this.project,
                t.content(),
                this.title.content()
            );
        }
    }
}
