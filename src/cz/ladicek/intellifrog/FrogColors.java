package cz.ladicek.intellifrog;

import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FrogColors {
    public static final TextAttributesKey ILLEGAL = createTextAttributesKey("FROG_ILLEGAL", SyntaxHighlighterColors.INVALID_STRING_ESCAPE.getDefaultAttributes());
    public static final TextAttributesKey LINE_COMMENT = createTextAttributesKey("FROG_LINE_COMMENT", SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes());
    public static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("FROG_BLOCK_COMMENT", SyntaxHighlighterColors.JAVA_BLOCK_COMMENT.getDefaultAttributes());
    public static final TextAttributesKey DOC_COMMENT = createTextAttributesKey("FROG_DOC_COMMENT", SyntaxHighlighterColors.DOC_COMMENT.getDefaultAttributes());
    public static final TextAttributesKey NUMBER = createTextAttributesKey("FROG_NUMBER", SyntaxHighlighterColors.NUMBER.getDefaultAttributes());
    public static final TextAttributesKey STRING = createTextAttributesKey("FROG_STRING", SyntaxHighlighterColors.STRING.getDefaultAttributes());
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("FROG_KEYWORD", SyntaxHighlighterColors.KEYWORD.getDefaultAttributes());
    public static final TextAttributesKey PARENS = createTextAttributesKey("FROG_PARENS", SyntaxHighlighterColors.PARENTHS.getDefaultAttributes());
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("FROG_IDENTIFIER", CodeInsightColors.LOCAL_VARIABLE_ATTRIBUTES.getDefaultAttributes());

    public static final TextAttributesKey KNOWN_ATTRIBUTE = createTextAttributesKey("FROG_KNOWN_ATTRIBUTE", CodeInsightColors.TYPE_PARAMETER_NAME_ATTRIBUTES.getDefaultAttributes());
}
