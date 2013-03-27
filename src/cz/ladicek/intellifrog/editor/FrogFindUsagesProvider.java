package cz.ladicek.intellifrog.editor;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import cz.ladicek.intellifrog.parser.FrogLexer;
import cz.ladicek.intellifrog.parser.FrogParserDefinition;
import cz.ladicek.intellifrog.parser.FrogTypes;
import cz.ladicek.intellifrog.psi.FrogAttribute;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrogFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new FrogLexer(),
                TokenSet.create(FrogTypes.FROG_WORD),
                FrogParserDefinition.COMMENT_TOKENS,
                TokenSet.create(
                        FrogTypes.FROG_NULL,
                        FrogTypes.FROG_SIMPLE_STRING,
                        FrogTypes.FROG_MULTILINE_STRING,
                        FrogTypes.FROG_INTEGER,
                        FrogTypes.FROG_LONG,
                        FrogTypes.FROG_FLOAT,
                        FrogTypes.FROG_DOUBLE,
                        FrogTypes.FROG_TRUE,
                        FrogTypes.FROG_FALSE,
                        FrogTypes.FROG_BYTE_ARRAY
                ));
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof FrogAttribute;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    // these are not necessary, FrogElementDescriptionProvider is consulted first

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        return "";
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        return "";
    }
}
