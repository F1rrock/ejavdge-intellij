package org.ejavdge.out.preset;

import com.intellij.openapi.project.Project;
import org.ejavdge.error.InvariantViolation;
import org.ejavdge.out.IntellijErrorDialog;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.workspace.out.Out;

public final class EjavdgeErrorDialog implements Out {
    private final Out origin;

    public EjavdgeErrorDialog(final Project p) {
        this(new IntellijErrorDialog(p, "EJavdge"));
    }

    public EjavdgeErrorDialog(Out origin) {
        this.origin = origin;
    }

    @Override
    public void write(final Text t) throws InvariantViolation {
        this.origin.write(t);
    }
}
