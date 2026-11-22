package org.ejavdge.action;

import com.intellij.openapi.vfs.VfsUtil;
import org.ejavdge.app.App;
import org.ejavdge.scalar.text.Text;

import java.io.File;

public final class WithDirectoryRefresh implements App {
    private final App origin;
    private final Text directory;

    public WithDirectoryRefresh(final App a, final Text d) {
        this.origin = a;
        this.directory = d;
    }

    @Override
    public void run() {
        this.origin.run();
        VfsUtil.markDirtyAndRefresh(true, false, true, new File(this.directory.content()));
    }
}
