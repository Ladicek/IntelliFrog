package cz.ladicek.intellifrog.editor;

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;
import com.intellij.psi.TokenType;
import cz.ladicek.intellifrog.parser.FrogTypes;

public class FrogQuoteHandler extends SimpleTokenSetQuoteHandler {
    public FrogQuoteHandler() {
        super(FrogTypes.FROG_SIMPLE_STRING, TokenType.BAD_CHARACTER);
    }
}
