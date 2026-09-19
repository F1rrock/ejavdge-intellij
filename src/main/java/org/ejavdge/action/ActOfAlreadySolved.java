package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.AlreadySolved;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.settings.CredOfSettings;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.LocOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class ActOfAlreadySolved extends AnAction {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        new AlreadySolved(
            new LocOfSettings(this.state).location(),
            new CredOfSettings(this.state).credentials(),
            new IntellijOut(
                Objects.requireNonNull(event.getProject())
                    .getService(ConsoleWindow.class)
                    .console()
            )
        ).run();
    }
}
