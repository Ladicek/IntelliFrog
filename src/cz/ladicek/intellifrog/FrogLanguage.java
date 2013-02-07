package cz.ladicek.intellifrog;

import com.intellij.lang.Language;

public class FrogLanguage extends Language {
    public static final FrogLanguage INSTANCE = new FrogLanguage();

    public FrogLanguage() {
        super("Frog");
    }

    @Override
    public String getDisplayName() {
        return "SmartFrog";
    }
}
