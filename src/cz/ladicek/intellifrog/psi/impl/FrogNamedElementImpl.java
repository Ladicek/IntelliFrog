package cz.ladicek.intellifrog.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import cz.ladicek.intellifrog.psi.FrogNamedElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public abstract class FrogNamedElementImpl extends FrogElementImpl implements FrogNamedElement {
    public FrogNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public String getName() {
        return getNameIdentifier().getText();
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        return this;
    }

    @NonNls
    public int getTextOffset() {
        return getNameIdentifier().getTextOffset();
    }
}
