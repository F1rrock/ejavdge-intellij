package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.ejavdge.error.IntellijError;
import org.ejavdge.out.preset.EjavdgeErrorDialog;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.workspace.out.WritingOf;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class ActionWithReport extends AnAction {
    @Override
    public final void actionPerformed(final @NotNull AnActionEvent e) {
        try {
            this.perform(e);
        } catch (final IntellijError err) {
            new WritingOf(
                new Text.Of(Objects.toString(err.getMessage(), err.toString())),
                new EjavdgeErrorDialog(e.getProject())
            ).perform();
        }
    }

    protected abstract void perform(final @NotNull AnActionEvent e);
}
