package cz.ladicek.intellifrog.editor;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import cz.ladicek.intellifrog.psi.FrogAttribute;

public class FrogRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isSafeDeleteAvailable(PsiElement element) {
        return element instanceof FrogAttribute;
    }

    @Override
    public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
        return element instanceof FrogAttribute;
    }
}
