package cz.ladicek.intellifrog.editor;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProvider;
import com.intellij.util.PlatformIcons;
import cz.ladicek.intellifrog.psi.FrogAttribute;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FrogAttributePresentationProvider implements ItemPresentationProvider<FrogAttribute> {
    @Override
    public ItemPresentation getPresentation(final FrogAttribute item) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return item.isValid() ? item.getAttrName().getText() : null;
            }

            @Nullable
            @Override
            public String getLocationString() {
                return item.isValid() ? item.getContainingFile().getName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return PlatformIcons.ANONYMOUS_CLASS_ICON;
            }
        };
    }
}
