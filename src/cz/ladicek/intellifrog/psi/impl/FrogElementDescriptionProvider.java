package cz.ladicek.intellifrog.psi.impl;

import com.intellij.psi.ElementDescriptionLocation;
import com.intellij.psi.ElementDescriptionProvider;
import com.intellij.psi.PsiElement;
import com.intellij.usageView.UsageViewLongNameLocation;
import com.intellij.usageView.UsageViewNodeTextLocation;
import com.intellij.usageView.UsageViewShortNameLocation;
import com.intellij.usageView.UsageViewTypeLocation;
import cz.ladicek.intellifrog.psi.FrogAttribute;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrogElementDescriptionProvider implements ElementDescriptionProvider {
    @Nullable
    @Override
    public String getElementDescription(@NotNull PsiElement element, @NotNull ElementDescriptionLocation location) {
        if (location instanceof UsageViewTypeLocation) {
            if (element instanceof FrogAttribute) {
                return "Attribute";
            }
        } else if (location instanceof UsageViewNodeTextLocation
                || location instanceof UsageViewShortNameLocation
                || location instanceof UsageViewLongNameLocation) {
            if (element instanceof FrogAttribute) {
                return ((FrogAttribute) element).getName();
            }
        }

        return null;
    }
}
