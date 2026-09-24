package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.ejavdge.error.IntellijError;
import org.jetbrains.annotations.NotNull;

public abstract class ActionWithReport extends AnAction {
    @Override
    public final void actionPerformed(final @NotNull AnActionEvent e) {
        try {
            this.perform(e);
        } catch (final IntellijError err) {
            Messages.showErrorDialog(
                e.getProject(),
                err.getMessage(),
                "Ejavdge"
            );
        }
    }

    protected abstract void perform(final @NotNull AnActionEvent e);
}
