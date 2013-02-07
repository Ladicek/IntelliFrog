package cz.ladicek.intellifrog.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import cz.ladicek.intellifrog.psi.FrogElement;
import org.jetbrains.annotations.NotNull;

public class FrogElementImpl extends ASTWrapperPsiElement implements FrogElement {
    public FrogElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return getNode().getElementType().toString();
    }
}
