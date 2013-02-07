package cz.ladicek.intellifrog.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import cz.ladicek.intellifrog.FrogLanguage;
import cz.ladicek.intellifrog.psi.FrogTokenType;
import cz.ladicek.intellifrog.psi.impl.FrogFileImpl;
import org.jetbrains.annotations.NotNull;

public class FrogParserDefinition implements ParserDefinition {
    public static final IFileElementType FROG_FILE_ELEMENT_TYPE = new IFileElementType("FROG_FILE", FrogLanguage.INSTANCE);

    public static final TokenSet WHITESPACE_TOKENS = TokenSet.create(TokenType.WHITE_SPACE);

    public static final TokenSet KEYWORDS = TokenSet.create(
            FrogTypes.FROG_APPLY,
            FrogTypes.FROG_ASSERT,
            FrogTypes.FROG_ATTRIB,
            FrogTypes.FROG_CONSTANT,
            FrogTypes.FROG_DATA,
            FrogTypes.FROG_HERE,
            FrogTypes.FROG_HOST,
            FrogTypes.FROG_LAZY,
            FrogTypes.FROG_NULL,
            FrogTypes.FROG_OPTIONAL,
            FrogTypes.FROG_PARENT,
            FrogTypes.FROG_PROCESS,
            FrogTypes.FROG_PROPERTY,
            FrogTypes.FROG_IPROPERTY,
            FrogTypes.FROG_ENVPROPERTY,
            FrogTypes.FROG_IENVPROPERTY,
            FrogTypes.FROG_ROOT,
            FrogTypes.FROG_TBD,
            FrogTypes.FROG_THIS,
            FrogTypes.FROG_VAR,

            FrogTypes.FROG_IF,
            FrogTypes.FROG_THEN,
            FrogTypes.FROG_ELSE,
            FrogTypes.FROG_FI,
            FrogTypes.FROG_SWITCH,
            FrogTypes.FROG_END_SWITCH,

            FrogTypes.FROG_CODEBASE,
            FrogTypes.FROG_EXTENDS,
            FrogTypes.FROG_FALSE,
            FrogTypes.FROG_INCLUDE,
            FrogTypes.FROG_INCLUDE_OPTIONALLY,
            FrogTypes.FROG_TRUE
    );

    public static final TokenSet PARENS = TokenSet.create(
            FrogTypes.FROG_LEFT_BRACE,
            FrogTypes.FROG_RIGHT_BRACE,
            FrogTypes.FROG_LEFT_PAREN,
            FrogTypes.FROG_RIGHT_PAREN,
            FrogTypes.FROG_LEFT_BRACKET,
            FrogTypes.FROG_RIGHT_BRACKET,
            FrogTypes.FROG_START_VECTOR,
            FrogTypes.FROG_END_VECTOR
    );

    public static final IElementType LINE_COMMENT = new FrogTokenType("LINE_COMMENT");
    public static final IElementType BLOCK_COMMENT = new FrogTokenType("BLOCK_COMMENT");
    public static final IElementType DOC_COMMENT = new FrogTokenType("DOC_COMMENT");
    public static final TokenSet COMMENT_TOKENS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT, DOC_COMMENT);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new FrogLexer();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new FrogParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FROG_FILE_ELEMENT_TYPE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITESPACE_TOKENS;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENT_TOKENS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.create(FrogTypes.FROG_SIMPLE_STRING, FrogTypes.FROG_MULTILINE_STRING);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return FrogTypes.Factory.createElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new FrogFileImpl(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
