package cz.ladicek.intellifrog.editor;

import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import cz.ladicek.intellifrog.parser.FrogParserDefinition;
import org.jetbrains.annotations.Nullable;

public class FrogCommenter implements CodeDocumentationAwareCommenter {
    @Nullable
    @Override
    public String getLineCommentPrefix() {
        return "//";
    }

    @Nullable
    @Override
    public String getBlockCommentPrefix() {
        return "/*";
    }

    @Nullable
    @Override
    public String getBlockCommentSuffix() {
        return "*/";
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }

    @Nullable
    @Override
    public IElementType getLineCommentTokenType() {
        return FrogParserDefinition.LINE_COMMENT;
    }

    @Nullable
    @Override
    public IElementType getBlockCommentTokenType() {
        return FrogParserDefinition.BLOCK_COMMENT;
    }

    @Nullable
    @Override
    public IElementType getDocumentationCommentTokenType() {
        return FrogParserDefinition.DOC_COMMENT;
    }

    @Nullable
    @Override
    public String getDocumentationCommentPrefix() {
        return "/**";
    }

    @Nullable
    @Override
    public String getDocumentationCommentLinePrefix() {
        return "*";
    }

    @Nullable
    @Override
    public String getDocumentationCommentSuffix() {
        return "*/";
    }

    @Override
    public boolean isDocumentationComment(PsiComment element) {
        return element != null && element.getTokenType() == FrogParserDefinition.DOC_COMMENT;
    }
}
