package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.ProblemDescription;
import org.ejavdge.file.JdkFile;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.ResOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public final class ActOfProblemDescription extends AnAction {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void actionPerformed(final @NotNull AnActionEvent event) {
        ApplicationManager.getApplication().saveAll();
        new ProblemDescription(
            new ResOfSettings(this.state).resource(),
            new JdkFile(
                new File(
                    Objects.requireNonNull(
                        event.getData(CommonDataKeys.VIRTUAL_FILE)
                    ).getPath()
                )
            ),
            new IntellijOut(
                Objects.requireNonNull(event.getProject())
                    .getService(ConsoleWindow.class)
                    .console()
            )
        ).run();
    }
}
