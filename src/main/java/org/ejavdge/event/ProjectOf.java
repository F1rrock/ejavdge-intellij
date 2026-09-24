package org.ejavdge.event;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.ejavdge.scalar.IntellijScalar;
import org.ejavdge.error.IntellijError;
import org.jetbrains.annotations.NotNull;

public final class ProjectOf implements IntellijScalar<Project> {
    private final AnActionEvent event;

    public ProjectOf(final @NotNull AnActionEvent e) {
        this.event = e;
    }

    @Override
    public Project value() throws IntellijError {
        final var p = this.event.getProject();
        if (p == null) {
            throw new IntellijError("There is no set up project.");
        }
        return p;
    }
}
