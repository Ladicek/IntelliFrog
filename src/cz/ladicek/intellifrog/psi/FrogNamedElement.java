package cz.ladicek.intellifrog.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.NotNull;

public interface FrogNamedElement extends FrogElement, PsiNameIdentifierOwner {
    @NotNull
    @Override
    String getName();

    @NotNull
    @Override
    PsiElement getNameIdentifier();
}
