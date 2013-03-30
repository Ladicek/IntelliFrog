package cz.ladicek.intellifrog.editor;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.ContainerUtil;
import cz.ladicek.intellifrog.parser.FrogParserDefinition;

import java.util.Set;

public class FrogNamesValidator implements NamesValidator {
    private static final Set<String> KEYWORDS = ContainerUtil.newHashSet();

    static {
        for (IElementType keyword : FrogParserDefinition.KEYWORDS.getTypes()) {
            KEYWORDS.add(keyword.toString());
        }
    }

    @Override
    public boolean isKeyword(String name, Project project) {
        return KEYWORDS.contains(name);
    }

    @Override
    public boolean isIdentifier(String name, Project project) {
        final int len = name.length();
        if (len == 0) return false;

        if (isKeyword(name, project)) return false;

        if (!isFrogIdentifierStart(name.charAt(0))) return false;

        for (int i = 1; i < len; i++) {
            if (!isFrogIdentifierPart(name.charAt(i))) return false;
        }

        return true;
    }

    private static boolean isFrogIdentifierStart(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean isFrogIdentifierPart(char c) {
        return isFrogIdentifierStart(c)
                || (c >= '0' && c <= '9')
                || c == '.' || c == '~' || c == '+' || c == '@' || c == '#'
                || c == '$' || c == '%' || c == '^' || c == '&' || c == '-' || c == '_';
    }
}
