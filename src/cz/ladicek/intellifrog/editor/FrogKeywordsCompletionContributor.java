package cz.ladicek.intellifrog.editor;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import cz.ladicek.intellifrog.parser.FrogParserDefinition;
import cz.ladicek.intellifrog.parser.FrogTypes;
import org.jetbrains.annotations.NotNull;

public class FrogKeywordsCompletionContributor extends CompletionContributor {
    public FrogKeywordsCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(FrogTypes.FROG_WORD),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                        for (IElementType keyword : FrogParserDefinition.KEYWORDS.getTypes()) {
                            result.addElement(LookupElementBuilder.create(keyword.toString()).withBoldness(true));
                        }
                    }
                });
    }
}
