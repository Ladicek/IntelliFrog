package cz.ladicek.intellifrog.psi.impl;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.JavaClassReferenceProvider;
import com.intellij.util.ProcessingContext;
import cz.ladicek.intellifrog.psi.FrogString;
import cz.ladicek.intellifrog.psi.SmartFrog;
import org.jetbrains.annotations.NotNull;

public class FrogStringReferenceContributor extends PsiReferenceContributor {
    private static final JavaClassReferenceProvider CLASS_REFERENCE_PROVIDER = new JavaClassReferenceProvider();

    static {
        CLASS_REFERENCE_PROVIDER.setSoft(true);
    }

    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(FrogString.class), new PsiReferenceProvider() {
            @NotNull
            @Override
            public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                FrogString string = (FrogString) element;
                String[] words = SmartFrog.value(string).split("\\s");
                if (words.length == 1) {
                    int offset = SmartFrog.offsetOfValue(string);
                    return CLASS_REFERENCE_PROVIDER.getReferencesByString(words[0], element, offset);
                }

                return PsiReference.EMPTY_ARRAY;
            }
        });
    }
}
