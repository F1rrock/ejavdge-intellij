package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.*;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.scalar.text.Notice;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.FormOfSettings;
import org.ejavdge.settings.ResOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.ejavdge.workspace.out.WritingOf;
import org.jetbrains.annotations.NotNull;

public final class ActOfSilentSubmit extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void perform(final @NotNull AnActionEvent e) {
        ApplicationManager.getApplication().saveAll();
        new AppOfEffect(
            new WritingOf(
                new Notice(
                    new RunningOf(
                        new SilentSubmit(
                            new CurrentFile(e).value(),
                            new FormOfSettings(this.state).value(),
                            new ResOfSettings(this.state).value()
                        )
                    ),
                    new Text.Of("Sent!")
                ),
                new IntellijOut(
                    new ProjectOf(e)
                        .value()
                        .getService(ConsoleWindow.class)
                        .console()
                )
            )
        ).run();
    }
}
