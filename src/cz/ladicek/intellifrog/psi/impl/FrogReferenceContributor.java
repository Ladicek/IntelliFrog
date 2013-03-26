package cz.ladicek.intellifrog.psi.impl;

import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.JavaClassReferenceProvider;
import com.intellij.util.ProcessingContext;
import cz.ladicek.intellifrog.psi.FrogIncludes;
import cz.ladicek.intellifrog.psi.FrogLinkReference;
import cz.ladicek.intellifrog.psi.FrogString;
import cz.ladicek.intellifrog.psi.SmartFrog;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.patterns.StandardPatterns.not;

public class FrogReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                psiElement(FrogString.class).withParent(FrogIncludes.class),
                new FrogStringInInclude2FrogFile()
        );
        registrar.registerReferenceProvider(
                psiElement(FrogString.class).withParent(not(psiElement(FrogIncludes.class))),
                new FrogString2JavaClass()
        );
        registrar.registerReferenceProvider(
                psiElement(FrogLinkReference.class),
                new FrogLinkReference2FrogAttribute()
        );
    }

    private static class FrogStringInInclude2FrogFile extends PsiReferenceProvider {
        @NotNull
        @Override
        public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
            FrogString string = (FrogString) element;
            FrogIncludedFileReferenceImpl reference = new FrogIncludedFileReferenceImpl(string);
            return new PsiReference[]{reference};
        }
    }

    private static class FrogString2JavaClass extends PsiReferenceProvider {
        private static final JavaClassReferenceProvider CLASS_REFERENCE_PROVIDER = new JavaClassReferenceProvider();

        static {
            CLASS_REFERENCE_PROVIDER.setSoft(true);
        }

        @NotNull
        @Override
        public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
            FrogString string = (FrogString) element;
            String[] words = SmartFrog.value(string).split("\\s");
            if (words.length == 1) {
                int offset = SmartFrog.textRange(string).getStartOffset();
                return CLASS_REFERENCE_PROVIDER.getReferencesByString(words[0], element, offset);
            }

            return PsiReference.EMPTY_ARRAY;
        }
    }

    private static class FrogLinkReference2FrogAttribute extends PsiReferenceProvider {
        @NotNull
        @Override
        public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
            FrogLinkReference linkReference = (FrogLinkReference) element;
            FrogAttributeReferenceImpl reference = new FrogAttributeReferenceImpl(linkReference);
            return new PsiReference[]{reference};
        }
    }
}
