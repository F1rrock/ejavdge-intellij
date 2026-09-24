package org.ejavdge.event;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import org.ejavdge.error.IntellijError;
import org.ejavdge.file.ByteFile;
import org.ejavdge.file.JdkFile;
import org.ejavdge.scalar.IntellijScalar;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class CurrentFile implements IntellijScalar<ByteFile> {
    private final AnActionEvent event;

    public CurrentFile(final @NotNull AnActionEvent e) {
        this.event = e;
    }

    @Override
    public ByteFile value() throws IntellijError {
        final var file = this.event
            .getData(CommonDataKeys.VIRTUAL_FILE);
        if (file == null) {
            throw new IntellijError("Current file is not selected.");
        }
        return new JdkFile(
            new File(file.getPath())
        );
    }
}
