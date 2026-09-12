package org.ejavdge.widget;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public final class EjToolWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(
        @NotNull final Project project,
        @NotNull final ToolWindow window
    ) {
        window.getContentManager().addContent(
            ContentFactory.getInstance().createContent(
                project.getService(ConsoleWindow.class).component(),
                "",
                false
            )
        );
    }
}
