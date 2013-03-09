package cz.ladicek.intellifrog.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProviders;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import cz.ladicek.intellifrog.psi.FrogElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrogElementImpl extends ASTWrapperPsiElement implements FrogElement {
    public FrogElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return getNode().getElementType().toString();
    }

    @Override
    public ItemPresentation getPresentation() {
        return ItemPresentationProviders.getItemPresentation(this);
    }

    @Nullable
    public PsiReference getReference() {
        PsiReference[] references = getReferences();
        return references.length == 0 ? null : references[0];
    }

    @NotNull
    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }
}
