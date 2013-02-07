package cz.ladicek.intellifrog.editor;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import cz.ladicek.intellifrog.parser.FrogTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrogBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = {
            new BracePair(FrogTypes.FROG_LEFT_BRACE, FrogTypes.FROG_RIGHT_BRACE, true),
            new BracePair(FrogTypes.FROG_LEFT_BRACKET, FrogTypes.FROG_RIGHT_BRACKET, false),
            new BracePair(FrogTypes.FROG_LEFT_PAREN, FrogTypes.FROG_RIGHT_PAREN, false),
            new BracePair(FrogTypes.FROG_START_VECTOR, FrogTypes.FROG_END_VECTOR, false)
    };

    @Override
    public BracePair[] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
