package cz.ladicek.intellifrog.util;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.libraries.LibraryUtil;
import com.intellij.openapi.vfs.VirtualFile;

public class AllSourceRoots {
    public static interface SourceRootProcessor {
        /**
         * @param sourceRoot the source root as a {@code VirtualFile}
         * @param isLibrary whether the source root is a library; is guaranteed to begin with {@code false} and once
         * it turns to {@code true}, it never turns back
         * @return {@code true} to continue processing or {@code false} to stop
         */
        boolean process(VirtualFile sourceRoot, boolean isLibrary);
    }

    public static void process(Project project, SourceRootProcessor processor) {
        // project modules
        for (Module module : ModuleManager.getInstance(project).getModules()) {
            ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
            for (VirtualFile sourceRoot : moduleRootManager.getSourceRoots()) {
                boolean shouldContinue = processor.process(sourceRoot, false);
                if (!shouldContinue) {
                    return;
                }
            }
        }

        // libraries
        for (VirtualFile library : LibraryUtil.getLibraryRoots(project, false, false)) {
            boolean shouldContinue = processor.process(library, true);
            if (!shouldContinue) {
                return;
            }
        }
    }
}
