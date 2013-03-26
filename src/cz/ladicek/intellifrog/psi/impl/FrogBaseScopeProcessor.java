package cz.ladicek.intellifrog.psi.impl;

import com.intellij.psi.scope.BaseScopeProcessor;
import com.intellij.util.containers.ContainerUtil;

import java.util.Set;

public abstract class FrogBaseScopeProcessor extends BaseScopeProcessor {
    final Set<String> alreadyProcessedImports = ContainerUtil.newHashSet(); // because of recursive imports
}
