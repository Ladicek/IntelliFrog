package cz.ladicek.intellifrog.psi.impl;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import cz.ladicek.intellifrog.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FrogPsiImplUtil {
    @NotNull
    public static PsiElement getNameIdentifier(@NotNull FrogAttribute attribute) {
        return attribute.getAttrName();
    }

    public static boolean processDeclarations(@NotNull FrogAttribute attribute,
                                              @NotNull PsiScopeProcessor processor, @NotNull ResolveState state,
                                              PsiElement lastParent, @NotNull PsiElement place) {

        if (!processor.execute(attribute, state)) {
            return false;
        }

        if (PsiTreeUtil.isContextAncestor(attribute, place, true)) {
            return ResolveUtil.processChildren(attribute, processor, state, lastParent, place);
        }

        return true;
    }

    public static boolean processDeclarations(@NotNull FrogAttributeList attributeList,
                                              @NotNull PsiScopeProcessor processor, @NotNull ResolveState state,
                                              PsiElement lastParent, @NotNull PsiElement place) {

        if (!processor.execute(attributeList, state)) {
            return false;
        }

        if (PsiTreeUtil.isContextAncestor(attributeList, place, true)) {
            return ResolveUtil.processChildren(attributeList, processor, state, lastParent, place);
        }

        return true;
    }

    public static boolean processDeclarations(@NotNull FrogIncludes includes,
                                              @NotNull PsiScopeProcessor processor, @NotNull ResolveState state,
                                              PsiElement lastParent, @NotNull PsiElement place) {

        if (!processor.execute(includes, state)) {
            return false;
        }

        List<FrogString> includesStringList = includes.getStringList(); // can either have 1 or 2 elements
        FrogString includeString = includesStringList.get(includesStringList.size() - 1); // last one is the right one
        FrogIncludedFileReferenceImpl importRef = new FrogIncludedFileReferenceImpl(includeString);
        PsiFile importedFile = (PsiFile) importRef.resolve();
        if (importedFile instanceof FrogFile) {
            VirtualFile virtualFile = importedFile.getOriginalFile().getVirtualFile();
            if (virtualFile != null) {
                String url = virtualFile.getUrl();
                FrogBaseScopeProcessor calcProcessor = (FrogBaseScopeProcessor) processor;
                if (!calcProcessor.alreadyProcessedImports.contains(url)) {
                    calcProcessor.alreadyProcessedImports.add(url);
                    FrogAttributeList topLevelAttributeList = PsiTreeUtil.findChildOfType(importedFile, FrogAttributeList.class);
                    return ResolveUtil.processChildren(topLevelAttributeList, processor, state, null, place);
                }
            }
        }
        return true;
    }
}
