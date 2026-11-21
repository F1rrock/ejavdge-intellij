package org.ejavdge.event;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.roots.ProjectFileIndex;
import org.ejavdge.error.IntellijError;
import org.ejavdge.scalar.IntellijScalar;
import org.ejavdge.scalar.text.Text;
import org.jetbrains.annotations.NotNull;

public final class SourceRootOf implements IntellijScalar<Text> {
    private final AnActionEvent event;

    public SourceRootOf(final @NotNull AnActionEvent e) {
        this.event = e;
    }

    @Override
    public Text value() throws IntellijError {
        final var file = this.event.getData(CommonDataKeys.VIRTUAL_FILE);
        if (file == null) {
            throw new IntellijError("Current file is not selected.");
        }
        final var project = new ProjectOf(this.event).value();
        final var root = ProjectFileIndex.getInstance(project).getSourceRootForFile(file);
        if (root == null) {
            throw new IntellijError("Current file is outside the source roots.");
        }
        return new Text.Of(root.getPath());
    }
}
