package cz.ladicek.intellifrog.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveState;
import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import cz.ladicek.intellifrog.FrogIcons;
import cz.ladicek.intellifrog.psi.FrogLinkReference;
import cz.ladicek.intellifrog.psi.FrogNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class FrogAttributeReferenceImpl extends PsiReferenceBase<FrogLinkReference> {
    public FrogAttributeReferenceImpl(FrogLinkReference element) {
        super(element, TextRange.from(0, element.getTextLength()));
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        if (myElement.getReferencePartList().size() == 1 && myElement.getReferencePartList().get(0).getWord() != null) {
            FrogFindAttributeDeclarationProcessor processor = new FrogFindAttributeDeclarationProcessor(myElement.getText());
            ResolveUtil.treeWalkUp(myElement, processor);
            return processor.getResult();
        }

        return null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        // alreadySeen is only a protection against multiple declarations of the same name
        final Set<String> alreadySeen = ContainerUtil.newHashSet();
        final List<LookupElement> results = ContainerUtil.newArrayList();
        ResolveUtil.treeWalkUp(myElement, new FrogBaseScopeProcessor() {
            @Override
            public boolean execute(@NotNull PsiElement element, ResolveState state) {
                if (element instanceof FrogNamedElement) {
                    FrogNamedElement namedElement = (FrogNamedElement) element;
                    String name = namedElement.getName();
                    if (!alreadySeen.contains(name)) {
                        alreadySeen.add(name);
                        results.add(buildLookupElement(namedElement));
                    }
                }
                return true;
            }
        });
        return ArrayUtil.toObjectArray(results);
    }

    private LookupElement buildLookupElement(FrogNamedElement element) {
        return LookupElementBuilder.create(element)
                .withIcon(FrogIcons.ATTRIBUTE)
                .withTypeText(element.getContainingFile().getName(), true);
    }
}
