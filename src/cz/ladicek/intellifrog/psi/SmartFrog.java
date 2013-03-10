package cz.ladicek.intellifrog.psi;

import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;

public class SmartFrog {
    @SuppressWarnings("ConstantConditions")
    @NotNull
    public static String value(@NotNull FrogString string) {
        if (string.getSimpleString() != null) {
            return string.getSimpleString().getText().replaceFirst("^\"", "").replaceFirst("\"$", "");
        } else if (string.getMultilineString() != null) {
            return string.getMultilineString().getText().replaceFirst("^##", "").replaceFirst("#$", "");
        } else {
            throw new IllegalStateException("string is always either simple_string or multiline_string");
        }
    }

    @NotNull
    public static TextRange textRange(@NotNull FrogString string) {
        if (string.getSimpleString() != null) {
            return TextRange.from(1, string.getTextLength() - 2);
        } else if (string.getMultilineString() != null) {
            return TextRange.from(2, string.getTextLength() - 3);
        } else {
            throw new IllegalStateException("string is always either simple_string or multiline_string");
        }
    }
}
