package cz.ladicek.intellifrog.editor;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import cz.ladicek.intellifrog.FrogColors;
import cz.ladicek.intellifrog.psi.FrogAttrName;
import org.jetbrains.annotations.NotNull;

public class FrogKnownAttributesAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof FrogAttrName)) {
            return;
        }

        String name = element.getText();
        if (KnownAttributes.ALL.contains(name)) {
            Annotation annotation = holder.createInfoAnnotation(element, null);
            annotation.setTextAttributes(FrogColors.KNOWN_ATTRIBUTE);
        }
    }
}
