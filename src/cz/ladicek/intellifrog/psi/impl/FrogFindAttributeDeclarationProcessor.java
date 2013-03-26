package cz.ladicek.intellifrog.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import cz.ladicek.intellifrog.psi.FrogNamedElement;
import org.jetbrains.annotations.NotNull;

public class FrogFindAttributeDeclarationProcessor extends FrogBaseScopeProcessor {
    private final String searchedName;
    private PsiElement result;

    public FrogFindAttributeDeclarationProcessor(String searchedName) {
        this.searchedName = searchedName;
    }

    @Override
    public boolean execute(@NotNull PsiElement element, ResolveState state) {
        if (element instanceof FrogNamedElement) {
            FrogNamedElement declaration = (FrogNamedElement) element;
            if (declaration.getName().equals(searchedName)) {
                result = declaration;
                return false;
            }
        }

        return true;
    }

    public PsiElement getResult() {
        return result;
    }
}
