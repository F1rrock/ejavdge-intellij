package org.ejavdge.out;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.ejavdge.error.InvariantViolation;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.workspace.out.Out;

public final class Info implements Out {
    private final Project project;
    private final Text about;

    public Info(final Project p, final String s) {
        this(p, new Text.Of(s));
    }

    public Info(final Project p, final Text t) {
        this.project = p;
        this.about = t;
    }

    @Override
    public void write(final Text t) throws InvariantViolation {
        Messages.showInfoMessage(
            project,
            t.content(),
            this.about.content()
        );
    }
}
