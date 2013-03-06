package cz.ladicek.intellifrog.editor;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.PlatformIcons;
import com.intellij.util.containers.ContainerUtil;
import cz.ladicek.intellifrog.psi.FrogAttribute;
import cz.ladicek.intellifrog.psi.FrogString;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class SfClassLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
        if (element instanceof FrogAttribute) {
            FrogAttribute attribute = (FrogAttribute) element;
            if (KnownAttributes.SF_CLASS.equals(attribute.getAttrName().getText())) {
                List<PsiClass> allFoundClasses = ContainerUtil.newArrayList();

                Project project = element.getProject();
                JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
                GlobalSearchScope globalScope = GlobalSearchScope.allScope(project);

                Collection<FrogString> strings = PsiTreeUtil.findChildrenOfType(attribute.getAttrValue(), FrogString.class);
                for (FrogString string : strings) {
                    String stringValue = null;
                    if (string.getSimpleString() != null) {
                        stringValue = string.getSimpleString().getText()
                                .replaceFirst("^\"", "").replaceFirst("\"$", "");
                    } else if (string.getMultilineString() != null) {
                        stringValue = string.getMultilineString().getText()
                                .replaceFirst("^##", "").replaceFirst("#$", "");
                    }
                    if (stringValue != null) {
                        PsiClass[] classesForString = javaPsiFacade.findClasses(stringValue, globalScope);
                        ContainerUtil.addAll(allFoundClasses, classesForString);
                    }
                }

                if (!allFoundClasses.isEmpty()) {
                    NavigationGutterIconBuilder<PsiElement> builder = NavigationGutterIconBuilder.create(PlatformIcons.CLASS_ICON)
                            .setTargets(allFoundClasses)
                            .setTooltipText("Navigate to component class");
                    result.add(builder.createLineMarkerInfo(element));
                }
            }
        }
    }
}
