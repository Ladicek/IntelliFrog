package cz.ladicek.intellifrog.psi;

import com.intellij.psi.tree.IElementType;
import cz.ladicek.intellifrog.FrogLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class FrogElementType extends IElementType {
    public FrogElementType(@NotNull @NonNls String debugName) {
        super(debugName, FrogLanguage.INSTANCE);
    }
}
