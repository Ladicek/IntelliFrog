package cz.ladicek.intellifrog.editor;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import cz.ladicek.intellifrog.FrogColors;
import cz.ladicek.intellifrog.parser.FrogLexer;
import cz.ladicek.intellifrog.parser.FrogParserDefinition;
import cz.ladicek.intellifrog.parser.FrogTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FrogSyntaxHighlighter extends SyntaxHighlighterBase {
    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FrogLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType == TokenType.BAD_CHARACTER) {
            return pack(FrogColors.ILLEGAL);
        } else if (tokenType == FrogParserDefinition.LINE_COMMENT) {
            return pack(FrogColors.LINE_COMMENT);
        } else if (tokenType == FrogParserDefinition.BLOCK_COMMENT) {
            return pack(FrogColors.BLOCK_COMMENT);
        } else if (tokenType == FrogParserDefinition.DOC_COMMENT) {
            return pack(FrogColors.DOC_COMMENT);
        } else if (tokenType == FrogTypes.FROG_INTEGER
                || tokenType == FrogTypes.FROG_LONG
                || tokenType == FrogTypes.FROG_FLOAT
                || tokenType == FrogTypes.FROG_DOUBLE) {
            return pack(FrogColors.NUMBER);
        } else if (tokenType == FrogTypes.FROG_SIMPLE_STRING
                || tokenType == FrogTypes.FROG_MULTILINE_STRING) {
            return pack(FrogColors.STRING);
        } else if (FrogParserDefinition.KEYWORDS.contains(tokenType)) {
            return pack(FrogColors.KEYWORD);
        } else if (FrogParserDefinition.PARENS.contains(tokenType)) {
            return pack(FrogColors.PARENS);
        } else if (tokenType == FrogTypes.FROG_WORD) {
            return pack(FrogColors.IDENTIFIER);
        } else {
            return EMPTY;
        }
    }
}
