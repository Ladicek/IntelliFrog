package cz.ladicek.intellifrog.psi;

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

    public static int offsetOfValue(@NotNull FrogString string) {
        if (string.getSimpleString() != null) {
            return 1;
        } else if (string.getMultilineString() != null) {
            return 2;
        } else {
            throw new IllegalStateException("string is always either simple_string or multiline_string");
        }
    }
}
