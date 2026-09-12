package org.ejavdge.widget;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;

import javax.swing.JComponent;

@Service(Service.Level.PROJECT)
public final class ConsoleWindow {
    private final ConsoleView console;

    public ConsoleWindow(final Project p) {
        this.console = TextConsoleBuilderFactory
            .getInstance()
            .createBuilder(p)
            .getConsole();
    }

    public JComponent component() {
        final var panel = new SimpleToolWindowPanel(true);
        panel.setContent(this.console.getComponent());
        return panel;
    }

    public ConsoleView console() {
        return this.console;
    }
}
