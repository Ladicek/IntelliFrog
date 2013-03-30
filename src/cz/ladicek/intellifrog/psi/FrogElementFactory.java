package cz.ladicek.intellifrog.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import cz.ladicek.intellifrog.FrogFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrogElementFactory {
    @Nullable
    public static FrogAttrName createAttrName(@NotNull Project project, @NotNull String name) {
        String text = name + " NULL;";
        FrogFile dummyFile = createFile(project, text);
        return PsiTreeUtil.findChildOfType(dummyFile, FrogAttrName.class);
    }

    @Nullable
    public static FrogLinkReference createLinkReference(@NotNull Project project, @NotNull String name) {
        String text = "dummy extends " + name + ";";
        FrogFile dummyFile = createFile(project, text);
        return PsiTreeUtil.findChildOfType(dummyFile, FrogLinkReference.class);
    }

    @NotNull
    private static FrogFile createFile(@NotNull Project project, @NotNull String text) {
        PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);
        return (FrogFile) psiFileFactory.createFileFromText("dummy.sf", FrogFileType.INSTANCE, text);
    }
}
