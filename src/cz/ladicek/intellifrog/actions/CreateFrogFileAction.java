package cz.ladicek.intellifrog.actions;

import com.intellij.ide.actions.CreateFileAction;
import cz.ladicek.intellifrog.FrogFileType;
import cz.ladicek.intellifrog.FrogIcons;
import org.jetbrains.annotations.Nullable;

public class CreateFrogFileAction extends CreateFileAction {
    private static final String NEW_FROG_FILE = "New SmartFrog File";

    public CreateFrogFileAction() {
        super(NEW_FROG_FILE, NEW_FROG_FILE, FrogIcons.FROG);
    }

    @Override
    public boolean isDumbAware() {
        return true;
    }

    @Nullable
    @Override
    protected String getDefaultExtension() {
        return FrogFileType.INSTANCE.getDefaultExtension();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CreateFrogFileAction;
    }
}
