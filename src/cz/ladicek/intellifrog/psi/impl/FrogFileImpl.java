package cz.ladicek.intellifrog.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import cz.ladicek.intellifrog.FrogFileType;
import cz.ladicek.intellifrog.FrogLanguage;
import cz.ladicek.intellifrog.psi.FrogFile;
import org.jetbrains.annotations.NotNull;

public class FrogFileImpl extends PsiFileBase implements FrogFile {
    public FrogFileImpl(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, FrogLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return FrogFileType.INSTANCE;
    }
}
