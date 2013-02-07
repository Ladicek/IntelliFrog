package cz.ladicek.intellifrog.editor;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import cz.ladicek.intellifrog.parser.FrogLexer;
import cz.ladicek.intellifrog.parser.FrogParserDefinition;
import cz.ladicek.intellifrog.parser.FrogTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FrogSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final TextAttributesKey ILLEGAL = createTextAttributesKey("FROG_ILLEGAL", SyntaxHighlighterColors.INVALID_STRING_ESCAPE.getDefaultAttributes());
    private static final TextAttributesKey LINE_COMMENT = createTextAttributesKey("FROG_LINE_COMMENT", SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes());
    private static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("FROG_BLOCK_COMMENT", SyntaxHighlighterColors.JAVA_BLOCK_COMMENT.getDefaultAttributes());
    private static final TextAttributesKey DOC_COMMENT = createTextAttributesKey("FROG_DOC_COMMENT", SyntaxHighlighterColors.DOC_COMMENT.getDefaultAttributes());
    private static final TextAttributesKey NUMBER = createTextAttributesKey("FROG_NUMBER", SyntaxHighlighterColors.NUMBER.getDefaultAttributes());
    private static final TextAttributesKey STRING = createTextAttributesKey("FROG_STRING", SyntaxHighlighterColors.STRING.getDefaultAttributes());
    private static final TextAttributesKey KEYWORD = createTextAttributesKey("FROG_KEYWORD", SyntaxHighlighterColors.KEYWORD.getDefaultAttributes());
    private static final TextAttributesKey PARENS = createTextAttributesKey("FROG_PARENS", SyntaxHighlighterColors.PARENTHS.getDefaultAttributes());
    private static final TextAttributesKey VARIABLE = createTextAttributesKey("FROG_VARIABLE", CodeInsightColors.LOCAL_VARIABLE_ATTRIBUTES.getDefaultAttributes());

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FrogLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType == TokenType.BAD_CHARACTER) {
            return pack(ILLEGAL);
        } else if (tokenType == FrogParserDefinition.LINE_COMMENT) {
            return pack(LINE_COMMENT);
        } else if (tokenType == FrogParserDefinition.BLOCK_COMMENT) {
            return pack(BLOCK_COMMENT);
        } else if (tokenType == FrogParserDefinition.DOC_COMMENT) {
            return pack(DOC_COMMENT);
        } else if (tokenType == FrogTypes.FROG_INTEGER
                || tokenType == FrogTypes.FROG_LONG
                || tokenType == FrogTypes.FROG_FLOAT
                || tokenType == FrogTypes.FROG_DOUBLE) {
            return pack(NUMBER);
        } else if (tokenType == FrogTypes.FROG_SIMPLE_STRING
                || tokenType == FrogTypes.FROG_MULTILINE_STRING) {
            return pack(STRING);
        } else if (FrogParserDefinition.KEYWORDS.contains(tokenType)) {
            return pack(KEYWORD);
        } else if (FrogParserDefinition.PARENS.contains(tokenType)) {
            return pack(PARENS);
        } else if (tokenType == FrogTypes.FROG_WORD) {
            return pack(VARIABLE);
        } else {
            return EMPTY;
        }
    }
}
