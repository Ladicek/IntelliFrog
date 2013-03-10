package cz.ladicek.intellifrog.editor;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProvider;
import com.intellij.navigation.LocationPresentation;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import cz.ladicek.intellifrog.FrogIcons;
import cz.ladicek.intellifrog.psi.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class FrogAttributePresentationProvider implements ItemPresentationProvider<FrogAttribute> {
    @Override
    public ItemPresentation getPresentation(FrogAttribute item) {
        return new FrogAttributePresentation(item);
    }

    private static class FrogAttributePresentation implements ItemPresentation, LocationPresentation {
        private final FrogAttribute attribute;

        private FrogAttributePresentation(FrogAttribute attribute) {
            this.attribute = attribute;
        }

        @Nullable
        @Override
        public String getPresentableText() {
            return attribute.isValid() ? attribute.getAttrName().getText() : null;
        }

        @Nullable
        @Override
        public String getLocationString() {
            if (!attribute.isValid()) {
                return null;
            }

            FrogAttrValue attrValue = attribute.getAttrValue();
            if (attrValue.getComponent() != null) {
                FrogComponent component = attrValue.getComponent();
                if (component != null) {
                    List<FrogLinkReference> linkReferences = ContainerUtil.newArrayList();

                    FrogLinkReference linkReference = component.getLinkReference();
                    if (linkReference != null) {
                        linkReferences.add(linkReference);
                    }
                    FrogComponentRest componentRest = component.getComponentRest();
                    if (componentRest != null) {
                        linkReferences.addAll(componentRest.getLinkReferenceList());
                    }

                    return "extends " + StringUtil.join(linkReferences, new Function<FrogLinkReference, String>() {
                        @Override
                        public String fun(FrogLinkReference linkReference) {
                            return linkReference.getText();
                        }
                    }, ", ");
                }
            }
            return null;
        }

        @Nullable
        @Override
        public Icon getIcon(boolean unused) {
            return FrogIcons.ATTRIBUTE;
        }

        @Override
        public String getLocationPrefix() {
            return " ";
        }

        @Override
        public String getLocationSuffix() {
            return "";
        }
    }
}
