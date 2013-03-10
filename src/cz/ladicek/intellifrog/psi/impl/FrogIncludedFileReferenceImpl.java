package cz.ladicek.intellifrog.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import cz.ladicek.intellifrog.FrogFileType;
import cz.ladicek.intellifrog.FrogIcons;
import cz.ladicek.intellifrog.psi.FrogString;
import cz.ladicek.intellifrog.psi.SmartFrog;
import cz.ladicek.intellifrog.util.AllSourceRoots;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static cz.ladicek.intellifrog.util.AllSourceRoots.SourceRootProcessor;

public class FrogIncludedFileReferenceImpl extends PsiPolyVariantReferenceBase<FrogString> {
    public FrogIncludedFileReferenceImpl(@NotNull FrogString element) {
        super(element, SmartFrog.textRange(element));
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        String path = SmartFrog.value(myElement);
        if (StringUtil.startsWithChar(path, VfsUtil.VFS_PATH_SEPARATOR)) {
            path = path.substring(1);
        }

        final List<PsiFile> results = ContainerUtil.newArrayList();

        Project project = myElement.getProject();
        final PsiManager psiManager = PsiManager.getInstance(project);
        final String finalPath = path;
        AllSourceRoots.process(project, new SourceRootProcessor() {
            @Override
            public boolean process(VirtualFile sourceRoot, boolean isLibrary) {
                if (isLibrary && !results.isEmpty()) {
                    return false;
                }

                VirtualFile includedFile = sourceRoot.findFileByRelativePath(finalPath);
                if (includedFile != null) {
                    PsiFile includedPsiFile = psiManager.findFile(includedFile);
                    if (includedPsiFile != null) {
                        results.add(includedPsiFile);
                    }
                }
                return true;
            }
        });

        return PsiElementResolveResult.createResults(results);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        final List<LookupElement> results = ContainerUtil.newArrayList();

        Project project = myElement.getProject();
        GlobalSearchScope searchScope = GlobalSearchScope.allScope(project);
        for (final VirtualFile file : FileTypeIndex.getFiles(FrogFileType.INSTANCE, searchScope)) {
            AllSourceRoots.process(project, new SourceRootProcessor() {
                @Override
                public boolean process(VirtualFile sourceRoot, boolean isLibrary) {
                    String path = VfsUtil.getPath(sourceRoot, file, VfsUtil.VFS_PATH_SEPARATOR);
                    if (path != null && !path.startsWith("..")) {
                        results.add(LookupElementBuilder.create("/" + path).withIcon(FrogIcons.FILE));
                        return false;
                    }
                    return true;
                }
            });
        }

        return ArrayUtil.toObjectArray(results);
    }
}
