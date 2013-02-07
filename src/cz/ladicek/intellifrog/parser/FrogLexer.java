package cz.ladicek.intellifrog.parser;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;

public class FrogLexer extends FlexAdapter {
    public FrogLexer() {
        super(new _FrogLexer());
    }
}
