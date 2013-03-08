package cz.ladicek.intellifrog;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FrogFileType extends LanguageFileType {
    public static final FrogFileType INSTANCE = new FrogFileType();

    public FrogFileType() {
        super(FrogLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "SmartFrog";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "SmartFrog Language";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "sf";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return FrogIcons.FILE;
    }
}
