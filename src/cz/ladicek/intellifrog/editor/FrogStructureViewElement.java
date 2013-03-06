package cz.ladicek.intellifrog.editor;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import cz.ladicek.intellifrog.psi.FrogAttribute;
import cz.ladicek.intellifrog.psi.FrogAttributeList;
import cz.ladicek.intellifrog.psi.FrogFile;

import java.util.List;

public class FrogStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
    private PsiElement psiElement;

    public FrogStructureViewElement(PsiElement psiElement) {
        this.psiElement = psiElement;
    }

    @Override
    public Object getValue() {
        return psiElement;
    }

    @Override
    public void navigate(boolean requestFocus) {
        if (psiElement instanceof NavigationItem) {
            ((NavigationItem) psiElement).navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        return psiElement instanceof NavigationItem && ((NavigationItem) psiElement).canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return psiElement instanceof NavigationItem && ((NavigationItem) psiElement).canNavigateToSource();
    }

    @Override
    public String getAlphaSortKey() {
        return psiElement instanceof PsiNamedElement ? ((PsiNamedElement) psiElement).getName() : null;
    }

    @Override
    public ItemPresentation getPresentation() {
        return psiElement instanceof NavigationItem ? ((NavigationItem) psiElement).getPresentation() : null;
    }

    @Override
    public TreeElement[] getChildren() {
        if (psiElement instanceof FrogFile || psiElement instanceof FrogAttribute) {
            FrogAttributeList attributeList = PsiTreeUtil.findChildOfType(psiElement, FrogAttributeList.class);
            if (attributeList != null) {
                return new FrogStructureViewElement(attributeList).getChildren();
            }
        } else if (psiElement instanceof FrogAttributeList) {
            List<FrogAttribute> attributes = PsiTreeUtil.getChildrenOfTypeAsList(psiElement, FrogAttribute.class);
            List<TreeElement> treeElements = ContainerUtil.map(attributes, new Function<FrogAttribute, TreeElement>() {
                @Override
                public TreeElement fun(FrogAttribute attribute) {
                    return new FrogStructureViewElement(attribute);
                }
            });
            return treeElements.toArray(new TreeElement[treeElements.size()]);
        }
        return EMPTY_ARRAY;
    }
}
