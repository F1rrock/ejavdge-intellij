package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import org.ejavdge.app.App;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.items.Items;
import org.ejavdge.scalar.text.Concat;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.workspace.out.Out;
import org.ejavdge.workspace.out.WritingOf;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class BackgroundAction extends AnAction {
    private final App origin;
    private final Text message;
    private final Out out;

    public BackgroundAction(final App a, final String m, final Out o) {
        this(a, new Text.Of(m), o);
    }

    public BackgroundAction(final App a, final Text m, final Out o) {
        this.origin = a;
        this.message = new Concat(
            new Text.Of(": "),
            new Items.Of<>(
                new Text.Of("EJavdge"),
                m
            )
        );
        this.out = o;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        new Task.Backgroundable(
            new ProjectOf(e).value(), this.message.content(), false
        ) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                origin.run();
            }

            @Override
            public void onThrowable(@NotNull Throwable err) {
                new WritingOf(
                    new Text.Of(Objects.toString(err.getMessage(), err.toString())),
                    out
                ).perform();
            }
        }.queue();
    }
}
