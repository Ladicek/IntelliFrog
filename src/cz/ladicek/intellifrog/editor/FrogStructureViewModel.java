package cz.ladicek.intellifrog.editor;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class FrogStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
    public FrogStructureViewModel(@NotNull PsiFile psiFile) {
        super(psiFile, new FrogStructureViewElement(psiFile));
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return false;
    }
}
